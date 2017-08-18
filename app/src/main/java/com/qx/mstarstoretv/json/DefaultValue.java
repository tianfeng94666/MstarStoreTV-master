package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class DefaultValue {


        /**
         * modelColor : {"id":1,"title":"18K白"}
         * modelQuality : {"id":1,"title":"精品"}
         */

        private ModelColorBean modelColor;
        private ModelQualityBean modelQuality;

        public ModelColorBean getModelColor() {
            return modelColor;
        }

        public void setModelColor(ModelColorBean modelColor) {
            this.modelColor = modelColor;
        }

        public ModelQualityBean getModelQuality() {
            return modelQuality;
        }

        public void setModelQuality(ModelQualityBean modelQuality) {
            this.modelQuality = modelQuality;
        }

        public static class ModelColorBean {
            /**
             * id : 1
             * title : 18K白
             */

            private String id;
            private String title;

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
        }

        public static class ModelQualityBean {
            /**
             * id : 1
             * title : 精品
             */

            private String id;
            private String title;

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
        }

}
