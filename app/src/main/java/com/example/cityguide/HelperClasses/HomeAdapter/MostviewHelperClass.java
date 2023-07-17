package com.example.cityguide.HelperClasses.HomeAdapter;

public class MostviewHelperClass {


        int image;
        String title,description;

        public MostviewHelperClass(int image, String title, String description) {
            this.image = image;
            this.title = title;
            this.description = description;
        }

        public int getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public String getDecription() {
            return description;
        }
    }


