package com.arbresystems.appoint.segundoPlano.alterarConfiguracoes;

import android.os.Handler;
import android.os.Message;

public class AlterarConfiguracoes {

    // ArrayList que armazenará os resultados das buscas das tarefas assíncronas.
    //private ArrayList<Produto> produtosResult;

    public void start(IntentServiceCallbackAlterarConfiracoes callback) {
        // Callback para a comunicação com o IntentService.
        IntentServiceHandler handler = new IntentServiceHandler(callback);
        handler.sendMessage(obtemMessage(1));
    }

    public Message obtemMessage(int msgWhat) {
        Message message = new Message();
        message.what = msgWhat;
        return message;
    }

    private class IntentServiceHandler extends Handler {

        private final IntentServiceCallbackAlterarConfiracoes callback;

        public IntentServiceHandler(IntentServiceCallbackAlterarConfiracoes callback) {
            // Callback para a comunicação com o IntentService.
            this.callback = callback;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    // Código que busca os produtos aqui.
                    break;
                case 2:
                    // Código que busca os produtos por gênero aqui.
                    break;
            }
        }
    }
}