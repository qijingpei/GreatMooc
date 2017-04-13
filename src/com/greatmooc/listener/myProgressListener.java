package com.greatmooc.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import com.greatmooc.domain.fileUploadStatus;

public class myProgressListener implements ProgressListener {
	private HttpSession session;

	public myProgressListener(HttpServletRequest request) {
		session=request.getSession();
		fileUploadStatus status = new fileUploadStatus();
		session.setAttribute("status", status);
	}

	public void update(long pBytesRead, long pContentLength, int pItems) {
		fileUploadStatus status = (fileUploadStatus) session.getAttribute("status");
		status.setPBytesRead(pBytesRead);
		status.setPContentLength(pContentLength);
		status.setPItems(pItems);
	}

}
