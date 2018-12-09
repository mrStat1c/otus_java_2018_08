package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class Main {
    private static final String htmlIndexDirectory = "../HW12/public_html";//�����, � ������� ����� ����������� ������� (��������)
    private static final int port = 8090;

    public static void main(String[] args) throws Exception {

        //���������� ��� ����������� �������� (���������, ��� ����� ������ ����������� �������)
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(htmlIndexDirectory);

        //���������� ����������. ��������� ������� UserServlet
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(UserServlet.class, "/admin");

        //������������� ��� ������� ����, ���������� �������� � ���������� ���������
        Server server = new Server(port);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
