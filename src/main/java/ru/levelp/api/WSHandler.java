package ru.levelp.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.levelp.api.entities.BaseRequest;
import ru.levelp.api.entities.ResponseContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("wsHandler")
public class WSHandler {
    private Gson gson;
    private ProtocolValidator protocolValidator;
    private TokenValidator tokenValidator;
    private RequestExecutor requestExecutor;

    @Autowired
    public WSHandler(Gson gson,
                     ProtocolValidator protocolValidator,
                     TokenValidator tokenValidator,
                     RequestExecutor requestExecutor) {
        this.gson = gson;
        this.protocolValidator = protocolValidator;
        this.tokenValidator = tokenValidator;
        this.requestExecutor = requestExecutor;
    }

    //Пришел запрос в этот метод по сокету от клиента
    public void onRequestReceived(String jsonRequest) {
        try {
            BaseRequest baseRequest = gson.fromJson(jsonRequest, BaseRequest.class);
            protocolValidator.validate(baseRequest);
            String userId = tokenValidator.validate(baseRequest.getMethod(), baseRequest.getToken());
            ResponseContainer response = requestExecutor.execute(jsonRequest, baseRequest.getMethod(), userId); //TODO: userId argument
            response.setCode(200);
            response.setRequestId(baseRequest.getRequestId());
            String answer = gson.toJson(response);
            //send answer to client
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
