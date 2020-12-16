package com.yd.ydyun.module;

public class RequestModule {
    int com = 7;
    String path;
    User user;

    public RequestModule( String path) {
        this.path = path;
        User.Group_id id = new User.Group_id();
        id .group_id = path;

        User.Group_id[]  ids =   new User.Group_id[]{id};

        User user = new User();
        user.setGroups(ids);
        this.user = user;

    }

    static class User {
        Group_id[] groups = new Group_id[]{};

        public Group_id[] getGroups() {
            return groups;
        }

        public void setGroups(Group_id[] groups) {
            this.groups = groups;
        }

        static class Group_id{
            String group_id ;

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }
        }
    }


}
