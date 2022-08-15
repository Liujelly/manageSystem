package lsd.qiye.system.dataobject;

public class OutWarehouseDO {
    private long id;
    private String productId;
    private String productName;
    private String whereabouts;
    private long operand;
    private String operatorId;
    private String reviewerId;
    private String gmtCreated;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWhereabouts() {
        return whereabouts;
    }

    public void setWhereabouts(String whereabouts) {
        this.whereabouts = whereabouts;
    }

    public long getOperand() {
        return operand;
    }

    public void setOperand(long operand) {
        this.operand = operand;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }
    public String getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}
