package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class GetRingPartResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"modelItem":{"id":"","title":"","price":"","handSize":"","word":"","modelPic":[{"pics":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePics.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePicm.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePicb.png"}]},"modelParts":[{"pid":"","partSort":"1","partCount":"3","price":"","title":"头","pics":"http://appapi1.fanerweb.com/images/imageForApi/custom//partsExamplePics1.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicm1.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicb1.png"},{"pid":"","partSort":"2","partCount":"2","price":"","title":"接口","pics":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePics2.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicm2.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicb2.png"},{"pid":"","partSort":"3","partCount":"3","price":"","title":"圈","pics":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePics3.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicm3.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicb3.png"}],"modelpartCount":["3","2","3"],"modelPuritys":[{"id":49,"title":"14K黄白分色"},{"id":8,"title":"Au750白"},{"id":5,"title":"G18K白"},{"id":4,"title":"G18K黄"},{"id":6,"title":"G18K玫瑰金"},{"id":7,"title":"G18K玫瑰金分色"},{"id":9,"title":"G750白"},{"id":3,"title":"Pd950"},{"id":1,"title":"PT900"},{"id":2,"title":"PT950"},{"id":47,"title":"千足黄金"}]}
     */

    private String response;
    private int error;
    private String message;
    private DataBean data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * modelItem : {"id":"","title":"","price":"","handSize":"","word":"","modelPic":[{"pics":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePics.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePicm.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePicb.png"}]}
         * modelParts : [{"pid":"","partSort":"1","partCount":"3","price":"","title":"头","pics":"http://appapi1.fanerweb.com/images/imageForApi/custom//partsExamplePics1.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicm1.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicb1.png"},{"pid":"","partSort":"2","partCount":"2","price":"","title":"接口","pics":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePics2.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicm2.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicb2.png"},{"pid":"","partSort":"3","partCount":"3","price":"","title":"圈","pics":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePics3.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicm3.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/partsExamplePicb3.png"}]
         * modelpartCount : ["3","2","3"]
         * modelPuritys : [{"id":49,"title":"14K黄白分色"},{"id":8,"title":"Au750白"},{"id":5,"title":"G18K白"},{"id":4,"title":"G18K黄"},{"id":6,"title":"G18K玫瑰金"},{"id":7,"title":"G18K玫瑰金分色"},{"id":9,"title":"G750白"},{"id":3,"title":"Pd950"},{"id":1,"title":"PT900"},{"id":2,"title":"PT950"},{"id":47,"title":"千足黄金"}]
         */

        private ModelItemBean modelItem;
        private List<ModelPartsBean> modelParts;
        private List<String> modelpartCount;
        private List<ModelPuritysBean> modelPuritys;
        private List<String> handSizeData;
        private JewelStone jewelStone;

        public JewelStone getJewelStone() {
            return jewelStone;
        }

        public void setJewelStone(JewelStone jewelStone) {
            this.jewelStone = jewelStone;
        }

        public List<String> getHandSizeData() {
            return handSizeData;
        }

        public void setHandSizeData(List<String> handSizeData) {
            this.handSizeData = handSizeData;
        }

        public ModelItemBean getModelItem() {
            return modelItem;
        }

        public void setModelItem(ModelItemBean modelItem) {
            this.modelItem = modelItem;
        }

        public List<ModelPartsBean> getModelParts() {
            return modelParts;
        }

        public void setModelParts(List<ModelPartsBean> modelParts) {
            this.modelParts = modelParts;
        }

        public List<String> getModelpartCount() {
            return modelpartCount;
        }

        public void setModelpartCount(List<String> modelpartCount) {
            this.modelpartCount = modelpartCount;
        }

        public List<ModelPuritysBean> getModelPuritys() {
            return modelPuritys;
        }

        public void setModelPuritys(List<ModelPuritysBean> modelPuritys) {
            this.modelPuritys = modelPuritys;
        }

        public static class ModelItemBean {
            /**
             * id :
             * title :
             * price :
             * handSize :
             * word :
             * modelPic : [{"pics":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePics.png","picm":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePicm.png","picb":"http://appapi1.fanerweb.com/images/imageForApi/custom/modelExamplePicb.png"}]
             */

            private String id;
            private String title;
            private String price;
            private String handSize;
            private String word;
            private List<ModelPicBean> modelPic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getHandSize() {
                return handSize;
            }

            public void setHandSize(String handSize) {
                this.handSize = handSize;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public List<ModelPicBean> getModelPic() {
                return modelPic;
            }

            public void setModelPic(List<ModelPicBean> modelPic) {
                this.modelPic = modelPic;
            }

        }



        public static class ModelPuritysBean {
            /**
             * id : 49
             * title : 14K黄白分色
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
