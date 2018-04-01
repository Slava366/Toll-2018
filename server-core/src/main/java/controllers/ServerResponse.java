package controllers;

/**
 * Отклик
 */
public class ServerResponse {

    boolean success;


    /**
     * Конструктор
     * @param success
     */
    ServerResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
