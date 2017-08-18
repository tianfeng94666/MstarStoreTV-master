package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class StoneSearchResult {

    /**
     * data : {"certAuth":{"keyword":"certAuth","title":"证书机构","values":["GIA","IGI"]},"color":{"keyword":"color","title":"颜色","values":["D","E","F","G","H","I","J","K","L","N","M"]},"cut":{"keyword":"cut","title":"切工","values":["EX","VG","GD","FR"]},"fluorescence":{"keyword":"Fluorescence","title":"荧光","values":["N","F","M","S","VS"]},"polishing":{"keyword":"polishing","title":"抛光","values":["EX","VG","GD","FR"]},"price":{"keyword":"price","maximum":20000,"minimum":0,"title":"价格范围(元) 0-20000"},"purity":{"keyword":"purity","title":"净度","values":["VVS1","VVS2","VS1","VS2","SI1","SI2","SI3","I1"]},"shape":{"keyword":"shape","title":"形状","values":[{"name":"圆形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_1.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_1_2.png"},{"name":"公主方","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_2.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_2_2.png"},{"name":"雷迪恩","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_3.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_3_2.png"},{"name":"心形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_4.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_4_2.png"},{"name":"马眼形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_5.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_5_2.png"},{"name":"椭圆形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_6.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_6_2.png"},{"name":"梨形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_7.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_7_2.png"},{"name":"梯形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_8.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_8_2.png"},{"name":"祖母绿","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_9.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_9_2.png"},{"name":"三角形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_10.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_10_2.png"}]},"symmetric":{"keyword":"symmetric","title":"对称","values":["EX","VG","GD","FR"]},"weight":{"keyword":"weight","maximum":30,"minimum":0,"title":"钻石重量(克拉) 0-30"}}
     * error : 0
     * message :
     * response :
     */

    private DataBean data;
    private int error;
    private String message;
    private String response;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class DataBean {
        /**
         * certAuth : {"keyword":"certAuth","title":"证书机构","values":["GIA","IGI"]}
         * color : {"keyword":"color","title":"颜色","values":["D","E","F","G","H","I","J","K","L","N","M"]}
         * cut : {"keyword":"cut","title":"切工","values":["EX","VG","GD","FR"]}
         * fluorescence : {"keyword":"Fluorescence","title":"荧光","values":["N","F","M","S","VS"]}
         * polishing : {"keyword":"polishing","title":"抛光","values":["EX","VG","GD","FR"]}
         * price : {"keyword":"price","maximum":20000,"minimum":0,"title":"价格范围(元) 0-20000"}
         * purity : {"keyword":"purity","title":"净度","values":["VVS1","VVS2","VS1","VS2","SI1","SI2","SI3","I1"]}
         * shape : {"keyword":"shape","title":"形状","values":[{"name":"圆形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_1.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_1_2.png"},{"name":"公主方","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_2.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_2_2.png"},{"name":"雷迪恩","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_3.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_3_2.png"},{"name":"心形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_4.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_4_2.png"},{"name":"马眼形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_5.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_5_2.png"},{"name":"椭圆形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_6.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_6_2.png"},{"name":"梨形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_7.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_7_2.png"},{"name":"梯形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_8.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_8_2.png"},{"name":"祖母绿","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_9.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_9_2.png"},{"name":"三角形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_10.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_10_2.png"}]}
         * symmetric : {"keyword":"symmetric","title":"对称","values":["EX","VG","GD","FR"]}
         * weight : {"keyword":"weight","maximum":30,"minimum":0,"title":"钻石重量(克拉) 0-30"}
         */

        private CertAuthBean certAuth;
        private ColorBean color;
        private CutBean cut;
        private FluorescenceBean fluorescence;
        private PolishingBean polishing;
        private PriceBean price;
        private PurityBean purity;
        private ShapeBean shape;
        private SymmetricBean symmetric;
        private WeightBean weight;

        public CertAuthBean getCertAuth() {
            return certAuth;
        }

        public void setCertAuth(CertAuthBean certAuth) {
            this.certAuth = certAuth;
        }

        public ColorBean getColor() {
            return color;
        }

        public void setColor(ColorBean color) {
            this.color = color;
        }

        public CutBean getCut() {
            return cut;
        }

        public void setCut(CutBean cut) {
            this.cut = cut;
        }

        public FluorescenceBean getFluorescence() {
            return fluorescence;
        }

        public void setFluorescence(FluorescenceBean fluorescence) {
            this.fluorescence = fluorescence;
        }

        public PolishingBean getPolishing() {
            return polishing;
        }

        public void setPolishing(PolishingBean polishing) {
            this.polishing = polishing;
        }

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public PurityBean getPurity() {
            return purity;
        }

        public void setPurity(PurityBean purity) {
            this.purity = purity;
        }

        public ShapeBean getShape() {
            return shape;
        }

        public void setShape(ShapeBean shape) {
            this.shape = shape;
        }

        public SymmetricBean getSymmetric() {
            return symmetric;
        }

        public void setSymmetric(SymmetricBean symmetric) {
            this.symmetric = symmetric;
        }

        public WeightBean getWeight() {
            return weight;
        }

        public void setWeight(WeightBean weight) {
            this.weight = weight;
        }

        public static class CertAuthBean {
            /**
             * keyword : certAuth
             * title : 证书机构
             * values : ["GIA","IGI"]
             */

            private String keyword;
            private String title;
            private List<String> values;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getValues() {
                return values;
            }

            public void setValues(List<String> values) {
                this.values = values;
            }
        }

        public static class ColorBean {
            /**
             * keyword : color
             * title : 颜色
             * values : ["D","E","F","G","H","I","J","K","L","N","M"]
             */

            private String keyword;
            private String title;
            private List<String> values;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getValues() {
                return values;
            }

            public void setValues(List<String> values) {
                this.values = values;
            }
        }

        public static class CutBean {
            /**
             * keyword : cut
             * title : 切工
             * values : ["EX","VG","GD","FR"]
             */

            private String keyword;
            private String title;
            private List<String> values;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getValues() {
                return values;
            }

            public void setValues(List<String> values) {
                this.values = values;
            }
        }

        public static class FluorescenceBean {
            /**
             * keyword : Fluorescence
             * title : 荧光
             * values : ["N","F","M","S","VS"]
             */

            private String keyword;
            private String title;
            private List<String> values;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getValues() {
                return values;
            }

            public void setValues(List<String> values) {
                this.values = values;
            }
        }

        public static class PolishingBean {
            /**
             * keyword : polishing
             * title : 抛光
             * values : ["EX","VG","GD","FR"]
             */

            private String keyword;
            private String title;
            private List<String> values;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getValues() {
                return values;
            }

            public void setValues(List<String> values) {
                this.values = values;
            }
        }

        public static class PriceBean {

            /**
             * list : [{"key":"0,1","title":"1克拉"},{"key":"2,4","title":"2~4克拉"}]
             * subject : 克拉
             */

            private String subject;
            private List<KeyTitle> list;

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public List<KeyTitle> getList() {
                return list;
            }

            public void setList(List<KeyTitle> list) {
                this.list = list;
            }


        }

        public static class PurityBean {
            /**
             * keyword : purity
             * title : 净度
             * values : ["VVS1","VVS2","VS1","VS2","SI1","SI2","SI3","I1"]
             */

            private String keyword;
            private String title;
            private List<String> values;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getValues() {
                return values;
            }

            public void setValues(List<String> values) {
                this.values = values;
            }
        }

        public static class ShapeBean {
            /**
             * keyword : shape
             * title : 形状
             * values : [{"name":"圆形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_1.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_1_2.png"},{"name":"公主方","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_2.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_2_2.png"},{"name":"雷迪恩","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_3.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_3_2.png"},{"name":"心形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_4.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_4_2.png"},{"name":"马眼形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_5.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_5_2.png"},{"name":"椭圆形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_6.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_6_2.png"},{"name":"梨形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_7.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_7_2.png"},{"name":"梯形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_8.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_8_2.png"},{"name":"祖母绿","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_9.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_9_2.png"},{"name":"三角形","pic":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_10.png","pic1":"http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_10_2.png"}]
             */

            private String keyword;
            private String title;
            private List<ValuesBean> values;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ValuesBean> getValues() {
                return values;
            }

            public void setValues(List<ValuesBean> values) {
                this.values = values;
            }

            public static class ValuesBean {
                /**
                 * name : 圆形
                 * pic : http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_1.png
                 * pic1 : http://appapi.fanerweb.com//images/stoneSearCh/Diamonds_1_2.png
                 */

                private String name;
                private String pic;
                private String pic1;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getPic1() {
                    return pic1;
                }

                public void setPic1(String pic1) {
                    this.pic1 = pic1;
                }
            }
        }

        public static class SymmetricBean {
            /**
             * keyword : symmetric
             * title : 对称
             * values : ["EX","VG","GD","FR"]
             */

            private String keyword;
            private String title;
            private List<String> values;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getValues() {
                return values;
            }

            public void setValues(List<String> values) {
                this.values = values;
            }
        }

        public static class WeightBean {

            /**
             * list : [{"key":"0,1","title":"1克拉"},{"key":"2,4","title":"2~4克拉"}]
             * subject : 克拉
             */

            private String subject;
            private List<KeyTitle> list;

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public List<KeyTitle> getList() {
                return list;
            }

            public void setList(List<KeyTitle> list) {
                this.list = list;
            }


        }
    }
}
