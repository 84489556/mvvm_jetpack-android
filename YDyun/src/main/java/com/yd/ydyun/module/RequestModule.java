package com.yd.ydyun.module;

public class RequestModule {
    int cmd = 7;
    String path;
    User user;

    public RequestModule( String nodepath,String id) {
        this.path = "yuanda/node".concat(nodepath);

        User.Group_id gid = new User.Group_id();
        gid .group_id = nodepath;

        User.Group_id[]  ids =   new User.Group_id[]{gid};
        User user = new User();
        user.setGroups(ids);
        user.setId(id);
        this.user = user;

    }

    static class User {
        String id ;
        Group_id[] groups = new Group_id[]{};

        public Group_id[] getGroups() {
            return groups;
        }

        public void setGroups(Group_id[] groups) {
            this.groups = groups;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
