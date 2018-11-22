    public class Place {
        private long code;
        private String status;
        private String name;

        public Place (long code, String name, String status)
        {
            this.code = code;
            this.status = status;
            this.name = name;
        }
        public long getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public void setName(String name) {
            this.name = name;
        }

    }






