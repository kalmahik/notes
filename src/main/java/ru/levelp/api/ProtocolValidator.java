package ru.levelp.api;

import org.springframework.stereotype.Component;
import ru.levelp.api.entities.BaseRequest;

@Component("protocolValidator")
public class ProtocolValidator {
    public static final String[] METHODS = new String[] {
            Method.AUTHORIZE,
            Method.REGISTRATION,
            Method.GET_USERS,
            Method.CREATE_NOTE,
            Method.EDIT_NOTE,
            Method.DELETE_NOTE,
            Method.GET_NOTES,
            Method.ADD_ACCESS_RIGHT,
            Method.REMOVE_ACCESS_RIGHT
    };

    public void validate(BaseRequest baseRequest) throws Exception {
        if (baseRequest.getRequestId() != null &&
                baseRequest.getMethod() != null) {
            for (String m : METHODS) {
                if (m.equals(baseRequest.getMethod())) {
                    return;
                }
            }
        }
        throw new Exception("protocol exeption");
    }

}
