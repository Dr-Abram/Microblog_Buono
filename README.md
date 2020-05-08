# Microblog_Buono
 micro blog per scuola
 
 Il progetto si appoggia su un database H2:    - database name: blogdb
                                               | username = sa
                                               | password = /
                                               
 - Indirizzo: http://localhost:8084/h2


Dalla pagina HOME reperibile all'indirizzo http://localhost:8084/ si può fare di Login e Registrazione, siccome la loro implementazione non era richiesta non ci ho perso molto tempo ed appena ho riscontrato problemi non sono più andato avanti. La registrazione in realtà funziona, o meglio fa la registrazione ed aggiunge il nuovo utente al database ma poi non restituisce la pagina PROFILE.HTML ma una valanga di errori ed è stato il motivo per cui non ho proseguito.
#####################################################################################
 
 Per fare le interrogazioni:
 
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
