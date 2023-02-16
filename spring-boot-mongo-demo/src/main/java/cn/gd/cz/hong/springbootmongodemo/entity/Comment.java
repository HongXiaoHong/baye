package cn.gd.cz.hong.springbootmongodemo.entity;

/**
 * 评论
 */
public class Comment {

//    父节点id
    private int parentId;
//    评论详情
    private String details;
//    点赞
    private int commend;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getCommend() {
        return commend;
    }

    public void setCommend(int commend) {
        this.commend = commend;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "parentId=" + parentId +
                ", details='" + details + '\'' +
                ", commend=" + commend +
                '}';
    }
}
