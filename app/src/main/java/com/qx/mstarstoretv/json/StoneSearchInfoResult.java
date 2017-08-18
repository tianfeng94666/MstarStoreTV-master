package com.qx.mstarstoretv.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class StoneSearchInfoResult extends Object implements Serializable {

    /**
     * response :
     * error : 0
     * message :
     * data : {"stone":{"list":[{"id":"1509","CertAuth":"","Weight":"0.3","Price":"13700","Shape":"","Color":"G","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1508","CertAuth":"","Weight":"0.33","Price":"10100","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1507","CertAuth":"","Weight":"0.5","Price":"13000","Shape":"","Color":"I","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1506","CertAuth":"","Weight":"0.25","Price":"9600","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1505","CertAuth":"","Weight":"1.02","Price":"47900","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1504","CertAuth":"","Weight":"0.41","Price":"14500","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1503","CertAuth":"GIA","Weight":"0.33","Price":"11700","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1502","CertAuth":"GIA","Weight":"0.38","Price":"12200","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1501","CertAuth":"GIA","Weight":"0.52","Price":"21700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1500","CertAuth":"GIA","Weight":"0.3","Price":"14000","Shape":"","Color":"H","Purity":"IF","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1499","CertAuth":"GIA","Weight":"0.31","Price":"15100","Shape":"","Color":"F","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1498","CertAuth":"","Weight":"0.38","Price":"14500","Shape":"","Color":" ","Purity":"","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1497","CertAuth":"GIA","Weight":"0.5","Price":"21500","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1496","CertAuth":"GIA","Weight":"0.37","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1495","CertAuth":"GIA","Weight":"0.31","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1494","CertAuth":"GIA","Weight":"0.31","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1493","CertAuth":"GIA","Weight":"0.38","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1492","CertAuth":"GIA","Weight":"0.33","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1491","CertAuth":"GIA","Weight":"0.51","Price":"19000","Shape":"","Color":"I","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1490","CertAuth":"GIA","Weight":"0.37","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1489","CertAuth":"GIA","Weight":"1.02","Price":"62000","Shape":"","Color":"G","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1488","CertAuth":"GIA","Weight":"0.38","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1487","CertAuth":"GIA","Weight":"0.39","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1486","CertAuth":"GIA","Weight":"0.38","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1485","CertAuth":"GIA","Weight":"0.36","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1484","CertAuth":"GIA","Weight":"0.34","Price":"9900","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1483","CertAuth":"","Weight":"0.39","Price":"11300","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1482","CertAuth":"","Weight":"1.01","Price":"47600","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1481","CertAuth":"","Weight":"0.34","Price":"11300","Shape":"","Color":"G","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1480","CertAuth":"","Weight":"0.3","Price":"11800","Shape":"","Color":"F","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""}],"list_count":"1509","headline":["勾选","克拉","形状","颜色","净度","切工","抛光","对称","荧光","证书"]}}
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

    public static class DataBean implements Serializable{
        /**
         * stone : {"list":[{"id":"1509","CertAuth":"","Weight":"0.3","Price":"13700","Shape":"","Color":"G","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1508","CertAuth":"","Weight":"0.33","Price":"10100","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1507","CertAuth":"","Weight":"0.5","Price":"13000","Shape":"","Color":"I","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1506","CertAuth":"","Weight":"0.25","Price":"9600","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1505","CertAuth":"","Weight":"1.02","Price":"47900","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1504","CertAuth":"","Weight":"0.41","Price":"14500","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1503","CertAuth":"GIA","Weight":"0.33","Price":"11700","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1502","CertAuth":"GIA","Weight":"0.38","Price":"12200","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1501","CertAuth":"GIA","Weight":"0.52","Price":"21700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1500","CertAuth":"GIA","Weight":"0.3","Price":"14000","Shape":"","Color":"H","Purity":"IF","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1499","CertAuth":"GIA","Weight":"0.31","Price":"15100","Shape":"","Color":"F","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1498","CertAuth":"","Weight":"0.38","Price":"14500","Shape":"","Color":" ","Purity":"","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1497","CertAuth":"GIA","Weight":"0.5","Price":"21500","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1496","CertAuth":"GIA","Weight":"0.37","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1495","CertAuth":"GIA","Weight":"0.31","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1494","CertAuth":"GIA","Weight":"0.31","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1493","CertAuth":"GIA","Weight":"0.38","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1492","CertAuth":"GIA","Weight":"0.33","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1491","CertAuth":"GIA","Weight":"0.51","Price":"19000","Shape":"","Color":"I","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1490","CertAuth":"GIA","Weight":"0.37","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1489","CertAuth":"GIA","Weight":"1.02","Price":"62000","Shape":"","Color":"G","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1488","CertAuth":"GIA","Weight":"0.38","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1487","CertAuth":"GIA","Weight":"0.39","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1486","CertAuth":"GIA","Weight":"0.38","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1485","CertAuth":"GIA","Weight":"0.36","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1484","CertAuth":"GIA","Weight":"0.34","Price":"9900","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1483","CertAuth":"","Weight":"0.39","Price":"11300","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1482","CertAuth":"","Weight":"1.01","Price":"47600","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1481","CertAuth":"","Weight":"0.34","Price":"11300","Shape":"","Color":"G","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1480","CertAuth":"","Weight":"0.3","Price":"11800","Shape":"","Color":"F","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""}],"list_count":"1509","headline":["勾选","克拉","形状","颜色","净度","切工","抛光","对称","荧光","证书"]}
         */

        private StoneBean stone;

        public StoneBean getStone() {
            return stone;
        }

        public void setStone(StoneBean stone) {
            this.stone = stone;
        }

        public static class StoneBean implements Serializable{
            /**
             * list : [{"id":"1509","CertAuth":"","Weight":"0.3","Price":"13700","Shape":"","Color":"G","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1508","CertAuth":"","Weight":"0.33","Price":"10100","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1507","CertAuth":"","Weight":"0.5","Price":"13000","Shape":"","Color":"I","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1506","CertAuth":"","Weight":"0.25","Price":"9600","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1505","CertAuth":"","Weight":"1.02","Price":"47900","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1504","CertAuth":"","Weight":"0.41","Price":"14500","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1503","CertAuth":"GIA","Weight":"0.33","Price":"11700","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1502","CertAuth":"GIA","Weight":"0.38","Price":"12200","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1501","CertAuth":"GIA","Weight":"0.52","Price":"21700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1500","CertAuth":"GIA","Weight":"0.3","Price":"14000","Shape":"","Color":"H","Purity":"IF","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1499","CertAuth":"GIA","Weight":"0.31","Price":"15100","Shape":"","Color":"F","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1498","CertAuth":"","Weight":"0.38","Price":"14500","Shape":"","Color":" ","Purity":"","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1497","CertAuth":"GIA","Weight":"0.5","Price":"21500","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1496","CertAuth":"GIA","Weight":"0.37","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1495","CertAuth":"GIA","Weight":"0.31","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1494","CertAuth":"GIA","Weight":"0.31","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1493","CertAuth":"GIA","Weight":"0.38","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1492","CertAuth":"GIA","Weight":"0.33","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1491","CertAuth":"GIA","Weight":"0.51","Price":"19000","Shape":"","Color":"I","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1490","CertAuth":"GIA","Weight":"0.37","Price":"13000","Shape":"","Color":"H","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1489","CertAuth":"GIA","Weight":"1.02","Price":"62000","Shape":"","Color":"G","Purity":"VVS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1488","CertAuth":"GIA","Weight":"0.38","Price":"12000","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1487","CertAuth":"GIA","Weight":"0.39","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1486","CertAuth":"GIA","Weight":"0.38","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1485","CertAuth":"GIA","Weight":"0.36","Price":"12700","Shape":"","Color":"G","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1484","CertAuth":"GIA","Weight":"0.34","Price":"9900","Shape":"","Color":"H","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1483","CertAuth":"","Weight":"0.39","Price":"11300","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1482","CertAuth":"","Weight":"1.01","Price":"47600","Shape":"","Color":"H","Purity":"VVS2","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1481","CertAuth":"","Weight":"0.34","Price":"11300","Shape":"","Color":"G","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""},{"id":"1480","CertAuth":"","Weight":"0.3","Price":"11800","Shape":"","Color":"F","Purity":"VS1","Cut":"","Polishing":"","Symmetric":"","Fluorescence":""}]
             * list_count : 1509
             * headline : ["勾选","克拉","形状","颜色","净度","切工","抛光","对称","荧光","证书"]
             */

            private String list_count;
            private List<ListBean> list;
            private List<String> headline;
            private String searchKey;

            public String getSearchKey() {
                return searchKey;
            }

            public void setSearchKey(String searchKey) {
                this.searchKey = searchKey;
            }

            public String getList_count() {
                return list_count;
            }

            public void setList_count(String list_count) {
                this.list_count = list_count;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<String> getHeadline() {
                return headline;
            }

            public void setHeadline(List<String> headline) {
                this.headline = headline;
            }

            public static class ListBean implements Serializable{
                /**
                 * id : 1509
                 * CertAuth :
                 * Weight : 0.3
                 * Price : 13700
                 * Shape :
                 * Color : G
                 * Purity : VVS1
                 * Cut :
                 * Polishing :
                 * Symmetric :
                 * Fluorescence :
                 */

                private String id;
                private String CertAuth;
                private String Weight;
                private String Price;
                private String Shape;
                private String Color;
                private String Purity;
                private String Cut;
                private String Polishing;
                private String Symmetric;
                private String Fluorescence;
                private String CertCode;
                private boolean ischeck;
                private String Percent;
                private ModelWeightRange modelWeightRange;

                public ModelWeightRange getModelWeightRange() {
                    return modelWeightRange;
                }

                public void setModelWeightRange(ModelWeightRange modelWeightRange) {
                    this.modelWeightRange = modelWeightRange;
                }

                public String getPercent() {
                    return Percent;
                }

                public void setPercent(String percent) {
                    Percent = percent;
                }

                public String getCertCode() {
                    return CertCode;
                }

                public void setCertCode(String certCode) {
                    CertCode = certCode;
                }

                public boolean ischeck() {
                    return ischeck;
                }

                public void setIscheck(boolean ischeck) {
                    this.ischeck = ischeck;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCertAuth() {
                    return CertAuth;
                }

                public void setCertAuth(String CertAuth) {
                    this.CertAuth = CertAuth;
                }

                public String getWeight() {
                    return Weight;
                }

                public void setWeight(String Weight) {
                    this.Weight = Weight;
                }

                public String getPrice() {
                    return Price;
                }

                public void setPrice(String Price) {
                    this.Price = Price;
                }

                public String getShape() {
                    return Shape;
                }

                public void setShape(String Shape) {
                    this.Shape = Shape;
                }

                public String getColor() {
                    return Color;
                }

                public void setColor(String Color) {
                    this.Color = Color;
                }

                public String getPurity() {
                    return Purity;
                }

                public void setPurity(String Purity) {
                    this.Purity = Purity;
                }

                public String getCut() {
                    return Cut;
                }

                public void setCut(String Cut) {
                    this.Cut = Cut;
                }

                public String getPolishing() {
                    return Polishing;
                }

                public void setPolishing(String Polishing) {
                    this.Polishing = Polishing;
                }

                public String getSymmetric() {
                    return Symmetric;
                }

                public void setSymmetric(String Symmetric) {
                    this.Symmetric = Symmetric;
                }

                public String getFluorescence() {
                    return Fluorescence;
                }

                public void setFluorescence(String Fluorescence) {
                    this.Fluorescence = Fluorescence;
                }
            }
        }
    }
}
