package com.example.demo.Helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenHelper jwtTokenHelper;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  )
    throws ServletException, IOException {
    //1 get token
    String requestToken = request.getHeader("Authorization");
    System.out.println(requestToken);

    String username = null;
    String token = null;
    if (requestToken != null && requestToken.startsWith("Bearer")) {
      token = requestToken.substring(7);
      try {
        username = this.jwtTokenHelper.getUsernameFromToken(token);
      } catch (IllegalArgumentException e) {
        System.out.println("Unable to get Jwt token");
      } catch (ExpiredJwtException e) {
        System.out.println("Jwt token has expired");
      } catch (MalformedJwtException e) {
        System.out.println("Invalid jwt");
      }
    } else {
      System.out.println("Jwt token does not begin with bearer");
    }
    //validated
    if (
      username != null &&
      SecurityContextHolder.getContext().getAuthentication() == null
    ) {
      final UserDetails userDetails =
        this.userDetailsService.loadUserByUsername(username);
      if (this.jwtTokenHelper.validateToken(token, userDetails)) {
        //token is valid

        UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );
        usernamePasswordAuthentication.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder
          .getContext()
          .setAuthentication(usernamePasswordAuthentication);
      } else {
        System.out.println("Token is not valid");
      }
    } else {
      System.out.println("username is null or context is null");
    }
    filterChain.doFilter(request, response);
  }
}
