package tw.rx.helperguides;

public class Model {

	private String flag;
	private String id;
	private String subject;
	private String typename;
	private String publishdate;
	private String content;
	
	public Model(String flag, String id, String subject,String typename,String publishdate) {
        this.flag = flag;
        this.id = id;
        this.subject = subject;
        this.typename = typename;
        this.publishdate = publishdate;
    }
 
    public String getSubject() {
        return subject;
    }
 
    public String getContent() {
        return content;
    }
 
    public String getDate() {
    	return publishdate;
    }
    
    public String getTypename() {
    	return typename;
    }
    
    public String getid() {
    	return id;
    }
}
