package com.qx.mstarstoretv.json;

import java.util.List;

public class ClassTypeFilerEntity {
    /**
     * mulSelect : 1
     * attributeList : [{"id":"14","title":"卡地亚","value":"14","groupKey":"category"},{"id":"15","title":"天使之吻","value":"15","groupKey":"category"},{"id":"16","title":"CNC","value":"16","groupKey":"category"},{"id":"17","title":"多围一","value":"17","groupKey":"category"},{"id":"18","title":"四爪","value":"18","groupKey":"category"},{"id":"19","title":"六爪","value":"19","groupKey":"category"},{"id":"20","title":"雪花爪","value":"20","groupKey":"category"},{"id":"21","title":"水滴爪","value":"21","groupKey":"category"},{"id":"22","title":"花头","value":"22","groupKey":"category"}]
     * id : 2
     * sort : 200
     * title : 特殊款
     * groupKey : category
     */
    private int mulSelect;
    private List<AttributeListEntity> attributeList;
    private String id;
    private int sort;
    private String title;
    private String groupKey;

    public void setMulSelect(int mulSelect) {
        this.mulSelect = mulSelect;
    }

    public void setAttributeList(List<AttributeListEntity> attributeList) {
        this.attributeList = attributeList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public int getMulSelect() {
        return mulSelect;
    }

    public List<AttributeListEntity> getAttributeList() {
        return attributeList;
    }

    public String getId() {
        return id;
    }

    public int getSort() {
        return sort;
    }

    public String getTitle() {
        return title;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public class AttributeListEntity {
        /**
         * id : 14
         * title : 卡地亚
         * value : 14
         * groupKey : category
         */
        /**
         * groupKey : category
         * id : 26
         * isSelect : 0
         * title : 辘珠边
         * value : 26
         */

        private String groupKey;
        private String id;
        private String isSelect;
        private String title;
        private String value;

        public String getGroupKey() {
            return groupKey;
        }

        public void setGroupKey(String groupKey) {
            this.groupKey = groupKey;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsSelect() {
            return isSelect;
        }

        public void setIsSelect(String isSelect) {
            this.isSelect = isSelect;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "AttributeListEntity{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", value='" + value + '\'' +
                    ", groupKey='" + groupKey + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ClassTypeFilerEntity{" +
                "mulSelect=" + mulSelect +
                ", attributeList=" + attributeList +
                ", id='" + id + '\'' +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", groupKey='" + groupKey + '\'' +
                '}';
    }
}