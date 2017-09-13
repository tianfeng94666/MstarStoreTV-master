package com.qx.mstarstoretv.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class ModelDetailResult implements Serializable{

    /**
     * data : {"stoneColor":[{"id":"1","title":"H+"},{"id":"2","title":"I-J"},{"id":"3","title":"H-I"},{"id":"4","title":"H-K"}],"stoneSpec":[{"id":"1","title":"10"},{"id":"2","title":"20"},{"id":"3","title":"30"},{"id":"4","title":"40"}],"stonePurity":[{"id":"1","title":"SI"},{"id":"2","title":"VS"},{"id":"3","title":"VS-VSS"}],"goldenPrice":[{"price":"355/g","title":"pt"},{"price":"356/g","title":"18k"}],"stoneShape":[{"id":"1","title":"圆形"},{"id":"2","title":"心形"},{"id":"3","title":"马形"},{"id":"4","title":"方形"},{"id":"5","title":"梯形"}],"stoneType":[{"id":"1","title":"钻石"},{"id":"2","title":"碎钻"},{"id":"3","title":"方钻"},{"id":"4","title":"锑钻"},{"id":"5","title":"马眼钻"}],"model":{"price":"5000","categoryTitle":"手镯","stoneA":{"specId":"3","number":null,"shapeId":"2","purityTitle":"SI","shapeTitle":"心形","colorId":"1","typeId":"1","specTitle":"30","colorTitle":"H+","typeTitle":"钻石","purityId":"1"},"stoneB":{"specId":null,"number":null,"shapeId":null,"purityTitle":"","shapeTitle":"","colorId":null,"typeId":"1","specTitle":"","colorTitle":"","typeTitle":"钻石","purityId":null},"stoneC":{"specId":null,"number":null,"shapeId":null,"purityTitle":"","shapeTitle":"","colorId":null,"typeId":"5","specTitle":"","colorTitle":"","typeTitle":"马眼钻","purityId":null},"title":"心安」 PT950铂金手镯( A36670-40)","pics":[{"id":"2","pic":"http://192.168.1.240:9112/Uploads/Pics/2016-09-21/dddd0.jpg"},{"id":"33","pic":"http://192.168.1.240:9112/Uploads/Pics/2016-09-21/5721b4f2N39738c83.jpg"}],"categoryId":"11","stone":{"specId":"1","number":null,"shapeId":"3","purityTitle":"SI","shapeTitle":"马形","colorId":"1","typeId":"1","specTitle":"10","colorTitle":"H+","typeTitle":"钻石","purityId":"1"}},"remarks":[{"id":"1","title":"提取信息","content":"不方便拿，请邮寄给我"},{"id":"2","title":"邮寄地址","content":"深圳市水贝工业区52号xx收"},{"id":"3","title":"没有这么办","content":"可以等待货到付款"}]}
     * response :
     * error : 0
     * message :
     */
    private DataEntity data;
    private String response;
    private int error;
    private String message;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEntity getData() {
        return data;
    }

    public String getResponse() {
        return response;
    }

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public class DataEntity implements Serializable{
        /**
         * stoneColor : [{"id":"1","title":"H+"},{"id":"2","title":"I-J"},{"id":"3","title":"H-I"},{"id":"4","title":"H-K"}]
         * stoneSpec : [{"id":"1","title":"10"},{"id":"2","title":"20"},{"id":"3","title":"30"},{"id":"4","title":"40"}]
         * stonePurity : [{"id":"1","title":"SI"},{"id":"2","title":"VS"},{"id":"3","title":"VS-VSS"}]
         * goldenPrice : [{"price":"355/g","title":"pt"},{"price":"356/g","title":"18k"}]
         * stoneShape : [{"id":"1","title":"圆形"},{"id":"2","title":"心形"},{"id":"3","title":"马形"},{"id":"4","title":"方形"},{"id":"5","title":"梯形"}]
         * stoneType : [{"id":"1","title":"钻石"},{"id":"2","title":"碎钻"},{"id":"3","title":"方钻"},{"id":"4","title":"锑钻"},{"id":"5","title":"马眼钻"}]
         * model : {"price":"5000","categoryTitle":"手镯","stoneA":{"specId":"3","number":null,"shapeId":"2","purityTitle":"SI","shapeTitle":"心形","colorId":"1","typeId":"1","specTitle":"30","colorTitle":"H+","typeTitle":"钻石","purityId":"1"},"stoneB":{"specId":null,"number":null,"shapeId":null,"purityTitle":"","shapeTitle":"","colorId":null,"typeId":"1","specTitle":"","colorTitle":"","typeTitle":"钻石","purityId":null},"stoneC":{"specId":null,"number":null,"shapeId":null,"purityTitle":"","shapeTitle":"","colorId":null,"typeId":"5","specTitle":"","colorTitle":"","typeTitle":"马眼钻","purityId":null},"title":"心安」 PT950铂金手镯( A36670-40)","pics":[{"id":"2","pic":"http://192.168.1.240:9112/Uploads/Pics/2016-09-21/dddd0.jpg"},{"id":"33","pic":"http://192.168.1.240:9112/Uploads/Pics/2016-09-21/5721b4f2N39738c83.jpg"}],"categoryId":"11","stone":{"specId":"1","number":null,"shapeId":"3","purityTitle":"SI","shapeTitle":"马形","colorId":"1","typeId":"1","specTitle":"10","colorTitle":"H+","typeTitle":"钻石","purityId":"1"}}
         * remarks : [{"id":"1","title":"提取信息","content":"不方便拿，请邮寄给我"},{"id":"2","title":"邮寄地址","content":"深圳市水贝工业区52号xx收"},{"id":"3","title":"没有这么办","content":"可以等待货到付款"}]
         */
        private List<StoneColorEntity> stoneColor;
        private List<StoneSpecEntity> stoneSpec;
        private List<StonePurityEntity> stonePurity;
        private List<GoldenPriceEntity> goldenPrice;
        private List<StoneShapeEntity> stoneShape;
        private List<StoneTypeEntity> stoneType;
        private List<ModelPuritys> modelPuritys;
        private ModelEntity model;
        private List<RemarksEntity> remarks;
        private String[] handSizeData;
        private int IsCanSelectStone;
        private JewelStone jewelStone;

        public List<ModelPuritys> getModelPuritys() {
            return modelPuritys;
        }

        public void setModelPuritys(List<ModelPuritys> modelPuritys) {
            this.modelPuritys = modelPuritys;
        }

        public int getIsCanSelectStone() {
            return IsCanSelectStone;
        }

        public void setIsCanSelectStone(int isCanSelectStone) {
            IsCanSelectStone = isCanSelectStone;
        }

        public JewelStone getJewelStone() {
            return jewelStone;
        }

        public void setJewelStone(JewelStone jewelStone) {
            this.jewelStone = jewelStone;
        }

        public String[] getHandSizeData() {
            return handSizeData;
        }

        public void setHandSizeData(String[] handSizeData) {
            this.handSizeData = handSizeData;
        }

        public void setStoneColor(List<StoneColorEntity> stoneColor) {
            this.stoneColor = stoneColor;
        }

        public void setStoneSpec(List<StoneSpecEntity> stoneSpec) {
            this.stoneSpec = stoneSpec;
        }

        public void setStonePurity(List<StonePurityEntity> stonePurity) {
            this.stonePurity = stonePurity;
        }

        public void setGoldenPrice(List<GoldenPriceEntity> goldenPrice) {
            this.goldenPrice = goldenPrice;
        }

        public void setStoneShape(List<StoneShapeEntity> stoneShape) {
            this.stoneShape = stoneShape;
        }

        public void setStoneType(List<StoneTypeEntity> stoneType) {
            this.stoneType = stoneType;
        }

        public void setModel(ModelEntity model) {
            this.model = model;
        }

        public void setRemarks(List<RemarksEntity> remarks) {
            this.remarks = remarks;
        }

        public List<StoneColorEntity> getStoneColor() {
            return stoneColor;
        }

        public List<StoneSpecEntity> getStoneSpec() {
            return stoneSpec;
        }

        public List<StonePurityEntity> getStonePurity() {
            return stonePurity;
        }

        public List<GoldenPriceEntity> getGoldenPrice() {
            return goldenPrice;
        }

        public List<StoneShapeEntity> getStoneShape() {
            return stoneShape;
        }

        public List<StoneTypeEntity> getStoneType() {
            return stoneType;
        }

        public ModelEntity getModel() {
            return model;
        }

        public List<RemarksEntity> getRemarks() {
            return remarks;
        }

        public class StoneColorEntity implements Serializable{
            /**
             * id : 1
             * title : H+
             */
            private String id;
            private String title;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }
        public class ModelPuritys implements Serializable {
            /**
             * id : 1
             * title : H+
             */
            private String id;
            private String title;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }
        public class StoneSpecEntity implements Serializable{
            /**
             * id : 1
             * title : 10
             */
            private String id;
            private String title;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }

        public class StonePurityEntity implements Serializable{
            /**
             * id : 1
             * title : SI
             */
            private String id;
            private String title;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }

        public class GoldenPriceEntity implements Serializable{
            /**
             * price : 355/g
             * title : pt
             */
            private String price;
            private String title;

            public void setPrice(String price) {
                this.price = price;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public String getTitle() {
                return title;
            }
        }

        public class StoneShapeEntity implements Serializable{
            /**
             * id : 1
             * title : 圆形
             */
            private String id;
            private String title;
            private String pic;
            private String pic1;

            public String getPic1() {
                return pic1;
            }

            public void setPic1(String pic1) {
                this.pic1 = pic1;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }

        public class StoneTypeEntity implements Serializable {
            /**
             * id : 1
             * title : 钻石
             */
            private String id;
            private String title;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }

        public class ModelEntity implements Serializable{
            /**
             * price : 5000
             * categoryTitle : 手镯
             * stoneA : {"specId":"3","number":null,"shapeId":"2","purityTitle":"SI","shapeTitle":"心形","colorId":"1","typeId":"1","specTitle":"30","colorTitle":"H+","typeTitle":"钻石","purityId":"1"}
             * stoneB : {"specId":null,"number":null,"shapeId":null,"purityTitle":"","shapeTitle":"","colorId":null,"typeId":"1","specTitle":"","colorTitle":"","typeTitle":"钻石","purityId":null}
             * stoneC : {"specId":null,"number":null,"shapeId":null,"purityTitle":"","shapeTitle":"","colorId":null,"typeId":"5","specTitle":"","colorTitle":"","typeTitle":"马眼钻","purityId":null}
             * title : 心安」 PT950铂金手镯( A36670-40)
             * pics : [{"id":"2","pic":"http://192.168.1.240:9112/Uploads/Pics/2016-09-21/dddd0.jpg"},{"id":"33","pic":"http://192.168.1.240:9112/Uploads/Pics/2016-09-21/5721b4f2N39738c83.jpg"}]
             * categoryId : 11
             * stone : {"specId":"1","number":null,"shapeId":"3","purityTitle":"SI","shapeTitle":"马形","colorId":"1","typeId":"1","specTitle":"10","colorTitle":"H+","typeTitle":"钻石","purityId":"1"}
             */
            private String price;
            private String categoryTitle;
            private StoneAEntity stoneA;
            private StoneBEntity stoneB;
            private StoneCEntity stoneC;
            private String title;
            private List<PicsEntity> pics;
            private String categoryId;
            private StoneEntity stone;
            private int isSelfStone;
            private String number;
            private String remark;
            private String handSize;
            private String weight;
            ModelWeightRange stoneWeightRange;

            public ModelWeightRange getRingWeightRange() {
                return stoneWeightRange;
            }

            public void setRingWeightRange(ModelWeightRange ringWeightRange) {
                this.stoneWeightRange = ringWeightRange;
            }
            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getHandSize() {
                return handSize;
            }

            public void setHandSize(String handSize) {
                this.handSize = handSize;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public int getIsSelfStone() {
                return isSelfStone;
            }

            public void setIsSelfStone(int isSelfStone) {
                this.isSelfStone = isSelfStone;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setCategoryTitle(String categoryTitle) {
                this.categoryTitle = categoryTitle;
            }

            public void setStoneA(StoneAEntity stoneA) {
                this.stoneA = stoneA;
            }

            public void setStoneB(StoneBEntity stoneB) {
                this.stoneB = stoneB;
            }

            public void setStoneC(StoneCEntity stoneC) {
                this.stoneC = stoneC;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setPics(List<PicsEntity> pics) {
                this.pics = pics;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public void setStone(StoneEntity stone) {
                this.stone = stone;
            }

            public String getPrice() {
                return price;
            }

            public String getCategoryTitle() {
                return categoryTitle;
            }

            public StoneAEntity getStoneA() {
                return stoneA;
            }

            public StoneBEntity getStoneB() {
                return stoneB;
            }

            public StoneCEntity getStoneC() {
                return stoneC;
            }

            public String getTitle() {
                return title;
            }

            public List<PicsEntity> getPics() {
                return pics;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public StoneEntity getStone() {
                return stone;
            }

            public class StoneAEntity extends StoneEntity implements Serializable{

                public StoneAEntity(StoneEntity stoneEntity) {
                    super(stoneEntity);
                }
            }

            public class StoneBEntity extends StoneEntity implements Serializable{

                public StoneBEntity(StoneEntity stoneEntity) {
                    super(stoneEntity);
                }
            }

            public class StoneCEntity extends StoneEntity implements Serializable{

                public StoneCEntity(StoneEntity stoneEntity) {
                    super(stoneEntity);
                }
            }

            public class PicsEntity implements Serializable{
                /**
                 * id : 2
                 * pic : http://192.168.1.240:9112/Uploads/Pics/2016-09-21/dddd0.jpg
                 */
                private String id;
                private String pic;
                private String picm;
                private String picb;

                public String getPicm() {
                    return picm;
                }

                public void setPicm(String picm) {
                    this.picm = picm;
                }

                public String getPicb() {
                    return picb;
                }

                public void setPicb(String picb) {
                    this.picb = picb;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getId() {
                    return id;
                }

                public String getPic() {
                    return pic;
                }
            }

        }

        public class RemarksEntity implements Serializable{
            /**
             * id : 1
             * title : 提取信息
             * content : 不方便拿，请邮寄给我
             */
            private String id;
            private String title;
            private String content;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getContent() {
                return content;
            }
        }
    }
}
