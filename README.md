# Microblog_Buono
 micro blog per scuola
 
 Il progetto si appoggia su un database H2:
                                               - username = sa
                                               - password = /
                                               
 - Indirizzo: http://localhost:8084/h2

 ######################################################################################################
 
 Per fare le interrogazioni:
 
   - cercare tutti gli utenti:
                         - http://localhost:8084/api/users
   - cercare un utente tramite ID:
                         - http://localhost:8084/api/users/{id}
   - cercare tutti i post di un utente:
                         - http://localhost:8084/api/users/{id}
    
   - cercare tutti i post:
                         - http://localhost:8084/api/posts
     
   - cercare tutti i commenti di un post:
                         - http://localhost:8084/api/posts/comments
                         
   - cercare tutti i commenti:
                         - http://localhost:8084/api/comments
