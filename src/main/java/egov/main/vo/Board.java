package egov.main.vo;

import java.util.Date;

public class Board {
	private long id;
	private String title;
	private String content;
	private String author;
	private Date regdate;
	private long hit;
	private String filename;
	private String fileurl;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public Board(long id, String title, String content, String author, Date regdate, long hit, String filename,
			String fileurl) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.regdate = regdate;
		this.hit = hit;
		this.filename = filename;
		this.fileurl = fileurl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public long getHit() {
		return hit;
	}

	public void setHit(long hit) {
		this.hit = hit;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	
	

}
