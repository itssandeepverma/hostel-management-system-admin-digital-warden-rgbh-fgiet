package com.sandeepmaucps.adminrgbh;


    public class Hero {

        int image;
        String name,email,mobile,branch,year,address,room,bed,fees,rollno,allot;

        public Hero(String name, String email, String mobile, String branch, String year, String address
                , String room, String bed, String fees, String rollno, String allot)
        {
            this.name = name;
            this.email=email;
            this.mobile=mobile;
            this.year=year;
            this.branch=branch;
            this.address=address;
            this.room=room;
            this.fees=fees;
            this.rollno=rollno;
            this.bed=bed;
            this.allot=allot;
        }

        public String getAllot() {
            return allot;
        }


        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public String getBranch() {
            return branch;
        }

        public String getYear() {
            return year;
        }

        public String getAddress() {
            return address;
        }

        public String getRoom() {
            return room;
        }

        public String getBed() {
            return bed;
        }

        public String getFees() {
            return fees;
        }

        public String getRollno() {
            return rollno;
        }

       /* int image;
        String name, team;

        public Hero(int image, String name, String team) {
            this.image = image;
            this.name = name;
            this.team = team;
        }

        public int getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getTeam() {
            return team;
        }*/
    }
