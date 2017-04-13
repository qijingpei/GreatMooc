package com.greatmooc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Course;
import com.greatmooc.domain.Video;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class ChapterDao {
	private QueryRunner qr = new TxQueryRunner();

	public List<Chapter> findAll(String user_id,String cou_id) throws SQLException {
		String sql = "select * from chapter where cou_id=? order by chap_num";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(),cou_id);
		List<Chapter> chapters = toChapterList(mapList);
		
		for(Chapter chapter:chapters){
			List<Video> videos = findVideos(chapter.getChap_id());
			if(user_id!=null)//若用户存在，则查找他的学习视频记录，并作设置
				videos = findVideosByUser(user_id, chapter.getChap_id(), videos);
			chapter.setVideos(videos);
		}
		return chapters;
	}		

	
	public List<Video> findVideos(String chap_id) throws SQLException {
		String sql = "select * from video where chap_id=? order by vid_num";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(),chap_id);
		List<Video> videos = toVideoList(mapList);
		return videos;
	}
	
	public Chapter load(String chap_id) throws SQLException {
		String sql = "select * from chapter where chap_id=?";
		Map<String, Object> map = qr.query(sql, new MapHandler(), chap_id);
		Chapter chapter = CommonUtils.toBean(map, Chapter.class);
		String cou_id = (String) map.get("cou_id");
		if(cou_id!=null){
			Course course = new Course();
			course.setCou_id(cou_id);
			chapter.setCourse(course);
		}
		return chapter;
	}
	//【后台管理】的通过cou_id查找章节个数
	public int findChapterCountByCour_id(String cou_id) throws SQLException {
		String sql = "select count(*) from chapter where cou_id=?";
		Number n = (Number) qr.query(sql, new ScalarHandler(),cou_id);
		return n==null?0:n.intValue();
	}

	//添加章节
	public void add(Chapter chapter) throws SQLException {
		String sql = "insert into chapter (chap_id,chap_name,chap_num,cou_id) values(?,?,?,?)";
		Object[] params = {chapter.getChap_id(),chapter.getChap_name(),
				chapter.getChap_num(),chapter.getCourse().getCou_id()};
		qr.update(sql, params);
	}
	//修改章节
	public void edit(Chapter chapter) throws SQLException {
		String sql = "update chapter set chap_name=?,chap_num=? where chap_id=?";
		Object[] params = {chapter.getChap_name(),chapter.getChap_num(),chapter.getChap_id()};
		qr.update(sql, params);
		
	}


	//把mapList转化为ChapterList
	private List<Chapter> toChapterList(List<Map<String, Object>> mapList) {
		List<Chapter> chapters = new ArrayList<Chapter>();
		for(Map<String, Object> map:mapList){
			Chapter chapter =toChapter(map);
			chapters.add(chapter);
		}
		return chapters;
	}

	//把map转化为chapter
	private Chapter toChapter(Map<String, Object> map) {
		Chapter chapter = CommonUtils.toBean(map, Chapter.class);
		String cou_id = (String) map.get("cou_id");
		Course course = new Course();
		course.setCou_id(cou_id);
		chapter.setCourse(course);
		return chapter;
	}
	
	//把mapList转化为VideorList
	private List<Video> toVideoList(List<Map<String, Object>> mapList) {
		List<Video> videos = new ArrayList<Video>();
		for(Map<String, Object> map:mapList){
			Video video =toVideo(map);
			videos.add(video);
		}
		return videos;
	}

		//把map转化为chapter
	private Video toVideo(Map<String, Object> map) {
		Video video = CommonUtils.toBean(map, Video.class);
		String chap_id = (String) map.get("chap_id");
		Chapter chapter = new Chapter();
		chapter.setChap_id(chap_id);
		video.setChapter(chapter);
		return video;
	}
	//通过user_id和chap_id查找用户学习过的视频，并把这些视频的learned属性设置为true
	public List<Video> findVideosByUser(String user_id,String chap_id,List<Video> videos){
		String sql = "select vid_id  from user_video where user_id=? and chap_id=?";
		try {
			List<Video> learned_videos = qr.query(sql, new BeanListHandler<Video>(Video.class), user_id,chap_id);
			for(Video learn:learned_videos){//对用户已经学过的视频遍历，将将章节中含有存在这些视频的learned设置为true
				for(Video video:videos){
					if(video.getVid_id().equals(learn.getVid_id())){
						video.setLearned(true);					
						}			
				}
			}
			return videos;
		} catch (SQLException e) {
			throw new RuntimeException("查找用户已经看过的视频出错了："+e);
		}
	}



	


	






}
