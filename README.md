# Microblog_Buono
 
 Il progetto si appoggia su un database H2:    - database name: blogdb
                                               | username = sa
                                               | password = /
                                               
 - Indirizzo: http://localhost:8084/h2

#####################################################################################
 
## Per fare le interrogazioni:
 
   - cercare tutti gli utenti:
                         - http://localhost:8084/api/users
   - cercare un utente tramite ID:
                         - http://localhost:8084/api/users/{id}
   - cercare tutti i post di un utente:
                         - http://localhost:8084/api/users/{id}/posts
   
   - cercare tutti i post:
                         - http://localhost:8084/api/posts
   - cercare un post tramite ID:
                         - http://localhost:8084/api/posts/{id}
   - cercare tutti i commenti di un post:
                         - http://localhost:8084/api/posts/{id}/comments
   
   - cercare tutti i commenti:
                         - http://localhost:8084/api/comments
   - cercare un commento tramite ID:
                         - http://localhost:8084/api/comments/{id}
                         
#####################################################################################

Per creare le risolse si può usare postman oppure il client (per ora si può interagire solo con gli utenti)
