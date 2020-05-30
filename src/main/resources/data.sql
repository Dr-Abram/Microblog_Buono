/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Abreham
 * Created: 07 mag 2020
 */

insert into users(username, email, password) values ('pippo', 'pippo@pippo.com', 'pippo');
insert into users(username, email, password) values ('pluto', 'pluto@pluto.com', 'pluto');
insert into posts(title, content, user_id) values ('pizza', 'la pizza buonisima', 1);
insert into comments(titleC, content, post_id, user_id) values ('disaccordo', 'a me non piace', 1, 2);

