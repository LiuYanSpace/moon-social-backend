package com.tothemoon.common.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@RefreshScope
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //拦截请求后进行token认证
        System.out.println("There is GateWay");

        //获取请求url
        //注意这里不要取URI URI拿到的是完整的前端请求url，包括端口号这些
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getPath().toString();

        //过滤请求url 即如果请求为/provider/login  则放行
        if (url.contains("/provider/login")) {
            return chain.filter(exchange);
        }

        //如果请求不是 则从请求头中获取token
        String token=request.getHeaders().getFirst("token");
        System.out.println(token);

        //如果token为空  则重写返回体
        //获取Response 为了重写返回体
        ServerHttpResponse response = exchange.getResponse();
        if (null == token) {
            return getVoidMono(response);
        }

        //如果有token  则解析token  解析成功说明token正确 则放行请求  否则重写返回体
        try {
            Claims claims = JwtUtil.parseJWT(token);
            String subject = claims.getSubject();
            System.out.println(subject);
        } catch (Exception e) {
            e.printStackTrace();
            return getVoidMono(response);
        }
        return chain.filter(exchange);
    }

    //封装响应体  注意此处ServerHttpResponse的包为import org.springframework.http.server.reactive.ServerHttpResponse
    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse) {
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        Map<String, String> entity = new HashMap<>();
        entity.put("code", "800");
        entity.put("message", "认证失败");
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(entity).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
