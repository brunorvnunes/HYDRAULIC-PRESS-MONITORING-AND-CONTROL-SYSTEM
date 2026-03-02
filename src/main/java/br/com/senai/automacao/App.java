package br.com.senai.automacao;

import org.eclipse.paho.client.mqttv3.*;

public class App {
    public static void main(String[] args) {
        try {
            // Define o endereço do Broker (o mesmo do ESP32)
            String broker = "tcp://broker.hivemq.com:1883";

            // Client ID deve ser ÚNICO (Requisito C7). Mude os números no final
            String clientId = "JavaBackend_SuaMatricula123";

            MqttClient client = new MqttClient(broker, clientId);
            client.connect(); // Estabelece conexão com o servidor
            System.out.println("Conectado ao Broker Industrial!");

            // ATENÇÃO: Coloque aqui EXATAMENTE o mesmo tópico que você usou no código do Wokwi (ESP32)
            String topico = "senai/ensaios/telemetria/seu_nome";

            // Inscreve-se no tópico para receber os dados
            client.subscribe(topico, (topic, msg) -> {

                // Converte a mensagem recebida de bytes para String
                String payload = new String(msg.getPayload());

                // EXIBIÇÃO EXATA EXIGIDA PELA PROVA
                System.out.println("Dados de Telemetria Coletados com Sucesso: " + payload);

            });

        } catch (MqttException e) {
            System.err.println("Erro na conexão MQTT: " + e.getMessage());
            e.printStackTrace();
        }
    }
}