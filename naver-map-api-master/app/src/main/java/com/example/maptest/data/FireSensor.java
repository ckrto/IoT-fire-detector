package com.example.maptest.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FireSensor {

        @SerializedName("id")
        private String id;
        @SerializedName("x")
        private double x;
        @SerializedName("y")
        private double y;
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }


}
