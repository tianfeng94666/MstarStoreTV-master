package com.qx.mstarstoretv.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class ShapeValues implements Serializable{

        /**
         * name : 圆形
         * pic : http://appapi2.fanerweb.com//images/stoneSearCh/Diamonds_1.png
         * pic1 : http://appapi2.fanerweb.com//images/stoneSearCh/Diamonds_1_2.png
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
