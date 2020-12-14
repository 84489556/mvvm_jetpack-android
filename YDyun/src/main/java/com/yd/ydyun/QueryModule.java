package com.yd.ydyun;

import android.text.TextUtils;

public class QueryModule {
    private String orderByKey;
    private String equalTo;
    private String startAt;
    private String endAt;
    private String limitToFirst;
    private String limitToLast;
    private String api = "/get";
    private boolean firstLevel = true;
    private String nodePath;



    public void setOrderByKey(String orderByKey) {
        this.orderByKey = orderByKey;
    }

    public void setEqualTo(String equalTo) {
        this.equalTo = equalTo;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public void setLimitToFirst(String limitToFirst) {
        this.limitToFirst = limitToFirst;
    }

    public void setLimitToLast(String limitToLast) {
        this.limitToLast = limitToLast;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setFirstLevel(boolean firstLevel) {
        this.firstLevel = firstLevel;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String orderByKey;
        private String equalTo;
        private String startAt;
        private String endAt;
        private String limitToFirst;
        private String limitToLast;
        private String nodePath;
        private String api ;
        private boolean firstLevel = true;
        public Builder orderByKey(String orderByKey) {
            if (TextUtils.isEmpty(orderByKey)) {
                this.orderByKey = "$child-".concat(orderByKey);
            } else {
                this.orderByKey = "$key";
            }
            return this;
        }

        public Builder equalTo(String equalTo) {
            this.equalTo = equalTo;
            return this;
        }

        public Builder startAt(String startAt) {
            this.startAt = startAt;
            return this;
        }

        public Builder endAt(String endAt) {
            this.endAt = endAt;
            return this;
        }

        public Builder limitToFirst(String limitToFirst) {
            this.limitToFirst = limitToFirst;
            return this;
        }

        public Builder limitToLast(String limitToLast) {
            this.limitToLast = limitToLast;
            return this;
        }
        public Builder nodePath(String nodePath) {
            this.nodePath = nodePath;
            return this;
        }
        public Builder api(String api) {
            this.api = api;
            return this;
        }
        public Builder firstLevel(boolean firstLevel) {
            this.firstLevel = firstLevel;
            return this;
        }
        public QueryModule build() {
            QueryModule module = new QueryModule();
            module.setEndAt(endAt);
            module.setEqualTo(equalTo);
            module.setLimitToFirst(limitToFirst);
            module.setLimitToLast(limitToLast);
            module.setStartAt(startAt);
            module.setOrderByKey(orderByKey);
            module.setNodePath(nodePath);
            module.setApi("/get");
            module.setFirstLevel(true);
            return module;
        }
    }


}
