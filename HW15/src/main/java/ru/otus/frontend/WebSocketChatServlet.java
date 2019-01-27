package ru.otus.frontend;

import org.eclipse.jetty.websocket.servlet.*;
import ru.otus.frontend.ChatWebSocketCreator;

import java.util.concurrent.TimeUnit;

//���� ������� ����� ������� �����-�� URL, ����� � ���� ����� ���� ����������
public class WebSocketChatServlet extends WebSocketServlet {
    private static final long LOGOUT_TIME = TimeUnit.MINUTES.toMillis(10);

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        webSocketServletFactory.setCreator(new ChatWebSocketCreator());
    }
}


//1. �������� ��� - ������
//2. ���-������ �������� ���-�����-������� (??)
//3. ���������� ������� ���������� � ������������� URL (� ���-����� �������� ??)
//4. ���-����� ������� �� ������� ������� ���-����� �� ����� ������� (??)(1 ������ ����� ���������� ����� ���-�������)
//���-����� �� ������� ������� + ���-����� �� ������� ������� = ����� ���� ����������


//����� ����� ���� ����� ������� �������
//5. ������ ������� � ���� ���-����� (??), ��� ����� ���� ����� �������, ���������� �� ������
//6. �� ������� ��������� ���-�����, ���-������ ������� � ������� ����������� � ���������� ���������� ����������

//��������� WebSocket
//��������� OnWebSocketConnect - �����, ������� ����������, ����� ��������� �������
//��������� OnWebSocketMessage -
//��������� OnWebSocketError -
//��������� OnWebSocketClose -
//��� ��� ��������� �� ������� �������, ������ ���������, ���� ���������� ������� �� ����������


//    � �� ����� ������� ���������� �� 4 �����:
//        - ������ (�� JS � ��������),
//        - FrontendService �� ������� � ������� ����� �� ������ ������� ��������� �� �������,
//        - DBService ������� ����� ���������� � ���� �
//        - MessageSystem, ������� �������� ��������� �� FrontendService � DBService � �������.