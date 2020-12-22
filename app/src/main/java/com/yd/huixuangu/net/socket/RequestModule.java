package com.yd.huixuangu.net.socket;

public class RequestModule {
    private int cmd = -1;
    private String path;
    private User user;
    private String id;
//    private Group_id[] groups;

//    public void setGroups(Group_id[] groups) {
//        this.groups = groups;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int cmd = -1;
        private String path;
        private User user;
        private String id;
        private Group_id[] groups;


        public Builder cmd(Group_id[] groups) {
            this.groups = groups;
            return this;
        }

        public Builder cmd(int cmd) {
            this.cmd = cmd;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public RequestModule build() {
            RequestModule requestModule = new RequestModule();
            requestModule.setCmd(cmd);
            requestModule.setPath("yuanda/node".concat(path));
            Group_id gid = Group_id.getInstance();
            gid.setGroup_id(path);
            groups  = new Group_id[]{gid};
            User user = User.getInstance();
            user.setId(id);
            user.setGroups(groups);
            requestModule.setUser(user);
            return requestModule;
        }
    }


    public RequestModule() {
//        this.path = "yuanda/node".concat(nodepath);
//
//        User.Group_id gid = new User.Group_id();
//        gid .group_id = nodepath;
//
//        User.Group_id[]  ids =   new User.Group_id[]{gid};
//        User user = new User();
//        user.setGroups(ids);
//        user.setId(id);
//        this.user = user;

    }

    static class User {

        private static User instance = null;

        public static User getInstance() {
            if (instance != null) return instance;
            instance = new User();
            return instance;
        }

        String id;
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


    }

    static class Group_id {
        private static Group_id instance = null;
        public static Group_id getInstance() {
            if (instance != null) return instance;
            instance = new Group_id();
            return instance;
        }


        private String group_id;

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }
    }


}
