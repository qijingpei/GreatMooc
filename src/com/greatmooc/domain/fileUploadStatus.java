package com.greatmooc.domain;

public class fileUploadStatus {
	private long pBytesRead = 0L;
	private long pContentLength = 0L;
	private int pItems;
	public fileUploadStatus(){
		pBytesRead = 0L;
		pContentLength = 0L;
	}
	public long getPBytesRead() {
		return pBytesRead;
	}
	public void setPBytesRead(long bytesRead) {
		pBytesRead = bytesRead;
	}
	public long getPContentLength() {
		return pContentLength;
	}
	public void setPContentLength(long contentLength) {
		pContentLength = contentLength;
	}
	public int getPItems() {
		return pItems;
	}
	public void setPItems(int items) {
		pItems = items;
	}
}
