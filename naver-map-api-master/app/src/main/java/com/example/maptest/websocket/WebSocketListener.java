package com.example.maptest.websocket;

import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;

public class WebSocketListener extends okhttp3.WebSocketListener {

    private static int NORMAL_CLOSURE_STATUS = 1000;

    @Override
    public void onOpen(WebSocket webSocket, Response repResponse){
        Log.d("Socket", "Scoket open");
        webSocket.send("android test");
    }
    @Override
    public void onMessage(WebSocket webSocket, String Text){
        Log.d("Socket", "Receiving : " + Text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString byteString){
        Log.d("Socket", "Receiving bytes : " + byteString);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason){
        Log.d("Socket", "Closing : " + code + " , " + reason);
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        webSocket.cancel();
    }

    @Override
    public void onFailure(WebSocket webSocket,Throwable t, Response response){
        Log.d("Socket", "Error : " + t.getMessage());
    }

}
