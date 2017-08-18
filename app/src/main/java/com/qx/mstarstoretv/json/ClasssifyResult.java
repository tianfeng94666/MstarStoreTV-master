package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class ClasssifyResult {


    /**
     * data : {"typeFiler":[{"mulSelect":1,"attributeList":[{"id":"14","title":"卡地亚","value":"14","groupKey":"category"},{"id":"15","title":"天使之吻","value":"15","groupKey":"category"},{"id":"16","title":"CNC","value":"16","groupKey":"category"},{"id":"17","title":"多围一","value":"17","groupKey":"category"},{"id":"18","title":"四爪","value":"18","groupKey":"category"},{"id":"19","title":"六爪","value":"19","groupKey":"category"},{"id":"20","title":"雪花爪","value":"20","groupKey":"category"},{"id":"21","title":"水滴爪","value":"21","groupKey":"category"},{"id":"22","title":"花头","value":"22","groupKey":"category"}],"id":"2","sort":200,"title":"特殊款","groupKey":"category"},{"mulSelect":1,"attributeList":[{"id":"23","title":"圆臂","value":"23","groupKey":"category"},{"id":"24","title":"小清新","value":"24","groupKey":"category"},{"id":"25","title":"一款三戴","value":"25","groupKey":"category"}],"id":"3","sort":300,"title":"系列","groupKey":"category"},{"mulSelect":0,"attributeList":[{"isSelect":"0","id":"1000000","title":"0-0.03","value":"0|0.03","groupKey":"weight"},{"isSelect":"0","id":"1000001","title":"0.03-0.07","value":"0.03|0.07","groupKey":"weight"},{"isSelect":"0","id":"1000002","title":"0.08-0.12","value":"0.08|0.12","groupKey":"weight"},{"isSelect":"0","id":"1000003","title":"0.13-0.17","value":"0.13|0.17","groupKey":"weight"},{"isSelect":"0","id":"1000004","title":"0.18-0.22","value":"0.18|0.22","groupKey":"weight"},{"isSelect":"0","id":"1000005","title":"0.25","value":"0.25","groupKey":"weight"},{"isSelect":"0","id":"1000006","title":"0.3","value":"0.3","groupKey":"weight"},{"isSelect":"0","id":"1000007","title":"0.4","value":"0.4","groupKey":"weight"},{"isSelect":"0","id":"1000008","title":"0.5","value":"0.5","groupKey":"weight"},{"isSelect":"0","id":"1000009","title":"0.6","value":"0.6","groupKey":"weight"},{"isSelect":"0","id":"1000010","title":"0.7","value":"0.7","groupKey":"weight"},{"isSelect":"0","id":"1000011","title":"0.8","value":"0.8","groupKey":"weight"},{"isSelect":"0","id":"1000012","title":"0.9","value":"0.9","groupKey":"weight"}],"sort":400,"title":"主石","groupKey":"weight"},{"mulSelect":0,"attributeList":[{"isSelect":"0","id":"1000013","title":"0-2000","value":"0|2000","groupKey":"price"},{"isSelect":"0","id":"1000014","title":"2000-4000","value":"2000|4000","groupKey":"price"},{"isSelect":"0","id":"1000015","title":"4000-8000","value":"4000|8000","groupKey":"price"},{"isSelect":"0","id":"1000016","title":"8000以上","value":"8000|","groupKey":"price"}],"sort":500,"title":"价格（不带主石）","groupKey":"price"},{"mulSelect":1,"attributeList":[{"id":"26","title":"辘珠边","value":"26","groupKey":"category"},{"id":"27","title":"喷砂","value":"27","groupKey":"category"},{"id":"28","title":"拉砂","value":"28","groupKey":"category"},{"id":"29","title":"封底","value":"29","groupKey":"category"},{"id":"30","title":"车花","value":"30","groupKey":"category"},{"id":"31","title":"镂空","value":"31","groupKey":"category"},{"id":"32","title":"车花片","value":"32","groupKey":"category"}],"id":"4","sort":600,"title":"工艺","groupKey":"category"},{"mulSelect":1,"attributeList":[{"id":"33","title":"畅销","value":"33","groupKey":"category"},{"id":"34","title":"经典","value":"34","groupKey":"category"},{"id":"35","title":"2015新品","value":"35","groupKey":"category"},{"id":"36","title":"2016新品","value":"36","groupKey":"category"},{"id":"37","title":"推荐","value":"37","groupKey":"category"}],"id":"5","sort":700,"title":"属性","groupKey":"category"},{"mulSelect":1,"attributeList":[{"id":"38","title":"直臂","value":"38","groupKey":"category"},{"id":"39","title":"扭臂","value":"39","groupKey":"category"}],"id":"6","sort":800,"title":"戒臂","groupKey":"category"}],"typeList":[{"id":"7","title":"女戒","value":"7","groupKey":"category"},{"id":"8","title":"男戒","value":"8","groupKey":"category"},{"id":"9","title":"耳钉","value":"9","groupKey":"category"},{"id":"10","title":"手链","value":"10","groupKey":"category"},{"id":"11","title":"手镯","value":"11","groupKey":"category"},{"id":"12","title":"吊坠","value":"12","groupKey":"category"},{"id":"13","title":"情侣戒","value":"13","groupKey":"category"}]}
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

    public class DataEntity {
        /**
         * typeFiler : [{"mulSelect":1,"attributeList":[{"id":"14","title":"卡地亚","value":"14","groupKey":"category"},{"id":"15","title":"天使之吻","value":"15","groupKey":"category"},{"id":"16","title":"CNC","value":"16","groupKey":"category"},{"id":"17","title":"多围一","value":"17","groupKey":"category"},{"id":"18","title":"四爪","value":"18","groupKey":"category"},{"id":"19","title":"六爪","value":"19","groupKey":"category"},{"id":"20","title":"雪花爪","value":"20","groupKey":"category"},{"id":"21","title":"水滴爪","value":"21","groupKey":"category"},{"id":"22","title":"花头","value":"22","groupKey":"category"}],"id":"2","sort":200,"title":"特殊款","groupKey":"category"},{"mulSelect":1,"attributeList":[{"id":"23","title":"圆臂","value":"23","groupKey":"category"},{"id":"24","title":"小清新","value":"24","groupKey":"category"},{"id":"25","title":"一款三戴","value":"25","groupKey":"category"}],"id":"3","sort":300,"title":"系列","groupKey":"category"},{"mulSelect":0,"attributeList":[{"isSelect":"0","id":"1000000","title":"0-0.03","value":"0|0.03","groupKey":"weight"},{"isSelect":"0","id":"1000001","title":"0.03-0.07","value":"0.03|0.07","groupKey":"weight"},{"isSelect":"0","id":"1000002","title":"0.08-0.12","value":"0.08|0.12","groupKey":"weight"},{"isSelect":"0","id":"1000003","title":"0.13-0.17","value":"0.13|0.17","groupKey":"weight"},{"isSelect":"0","id":"1000004","title":"0.18-0.22","value":"0.18|0.22","groupKey":"weight"},{"isSelect":"0","id":"1000005","title":"0.25","value":"0.25","groupKey":"weight"},{"isSelect":"0","id":"1000006","title":"0.3","value":"0.3","groupKey":"weight"},{"isSelect":"0","id":"1000007","title":"0.4","value":"0.4","groupKey":"weight"},{"isSelect":"0","id":"1000008","title":"0.5","value":"0.5","groupKey":"weight"},{"isSelect":"0","id":"1000009","title":"0.6","value":"0.6","groupKey":"weight"},{"isSelect":"0","id":"1000010","title":"0.7","value":"0.7","groupKey":"weight"},{"isSelect":"0","id":"1000011","title":"0.8","value":"0.8","groupKey":"weight"},{"isSelect":"0","id":"1000012","title":"0.9","value":"0.9","groupKey":"weight"}],"sort":400,"title":"主石","groupKey":"weight"},{"mulSelect":0,"attributeList":[{"isSelect":"0","id":"1000013","title":"0-2000","value":"0|2000","groupKey":"price"},{"isSelect":"0","id":"1000014","title":"2000-4000","value":"2000|4000","groupKey":"price"},{"isSelect":"0","id":"1000015","title":"4000-8000","value":"4000|8000","groupKey":"price"},{"isSelect":"0","id":"1000016","title":"8000以上","value":"8000|","groupKey":"price"}],"sort":500,"title":"价格（不带主石）","groupKey":"price"},{"mulSelect":1,"attributeList":[{"id":"26","title":"辘珠边","value":"26","groupKey":"category"},{"id":"27","title":"喷砂","value":"27","groupKey":"category"},{"id":"28","title":"拉砂","value":"28","groupKey":"category"},{"id":"29","title":"封底","value":"29","groupKey":"category"},{"id":"30","title":"车花","value":"30","groupKey":"category"},{"id":"31","title":"镂空","value":"31","groupKey":"category"},{"id":"32","title":"车花片","value":"32","groupKey":"category"}],"id":"4","sort":600,"title":"工艺","groupKey":"category"},{"mulSelect":1,"attributeList":[{"id":"33","title":"畅销","value":"33","groupKey":"category"},{"id":"34","title":"经典","value":"34","groupKey":"category"},{"id":"35","title":"2015新品","value":"35","groupKey":"category"},{"id":"36","title":"2016新品","value":"36","groupKey":"category"},{"id":"37","title":"推荐","value":"37","groupKey":"category"}],"id":"5","sort":700,"title":"属性","groupKey":"category"},{"mulSelect":1,"attributeList":[{"id":"38","title":"直臂","value":"38","groupKey":"category"},{"id":"39","title":"扭臂","value":"39","groupKey":"category"}],"id":"6","sort":800,"title":"戒臂","groupKey":"category"}]
         * typeList : [{"id":"7","title":"女戒","value":"7","groupKey":"category"},{"id":"8","title":"男戒","value":"8","groupKey":"category"},{"id":"9","title":"耳钉","value":"9","groupKey":"category"},{"id":"10","title":"手链","value":"10","groupKey":"category"},{"id":"11","title":"手镯","value":"11","groupKey":"category"},{"id":"12","title":"吊坠","value":"12","groupKey":"category"},{"id":"13","title":"情侣戒","value":"13","groupKey":"category"}]
         */
        private List<ClassTypeFilerEntity> typeFiler;
        private List<TypeListEntity> typeList;

        public void setTypeFiler(List<ClassTypeFilerEntity> typeFiler) {
            this.typeFiler = typeFiler;
        }

        public void setTypeList(List<TypeListEntity> typeList) {
            this.typeList = typeList;
        }

        public List<ClassTypeFilerEntity> getTypeFiler() {
            return typeFiler;
        }

        public List<TypeListEntity> getTypeList() {
            return typeList;
        }


        public class TypeListEntity {
            /**
             * id : 7
             * title : 女戒
             * value : 7
             * groupKey : category
             */
            private String id;
            private String title;
            private String value;
            private String groupKey;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public void setGroupKey(String groupKey) {
                this.groupKey = groupKey;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getValue() {
                return value;
            }

            public String getGroupKey() {
                return groupKey;
            }
        }
    }
}
