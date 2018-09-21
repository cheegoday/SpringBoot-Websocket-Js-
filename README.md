websocket.js支持以下功能：

1、断网无限重连

2、心跳机制

3、后端没有消息返回就自动断开连接

注意事项：MyWebSocket.java作为bean注入到spring中，每当有一个前端页面与websocket服务器建立连接，spring就会为MyWebSocket.java实例化一个对象，因此，websocket服务端在spring的管理下，默认不是单例模式！！！