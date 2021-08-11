package itix.rest.spark;

import com.google.gson.JsonElement;

public class StandardResponse {

    private StatusResponse status;
    private JsonElement data;

    public StandardResponse(JsonElement data) {
        this.data = data;
    }

    public StandardResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.data = data;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public enum StatusResponse {
        SUCCESS("Success"),
        ERROR("Error");

        private String status;

        StatusResponse(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StandardResponse{");
        sb.append("status=").append(status);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
