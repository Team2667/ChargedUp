// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * Do NOT add any static variables to this class, or any initialization at all. Unless you know what
 * you are doing, do not modify this file except to change the parameter class to the startRobot
 * call.
 */
public final class Main {
  private Main() {}

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
/** Funny ASCII art in the main file we don't touch                                                                                                                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                    ..^~~:..                                                                                                                    
                                                                                                                                                                                                                                            .:~7J5555Y?!~:.                    .^7J5PGGGGGPP5Y?~.                                                                                                               
                                                                                                                                                                                                                                        .~YPGGBGGGGGGGGGGP5J~.              ^J5GGGGGGGGGGGGGGGPP5Y~.                                                                                                            
                                                                                                                                                                                                                                      :?PGGGGGGGGGGGGGGGGGPPPY7.          ^5GGGGGGGGGGGGGGGGGGGPPP5Y7:                                                                                                          
                                                                                                                                                                                                                                    :JGGGGGGGGGGGGGGGGGGGGGPPP5Y?:      ^YGGGGGGGGGGGGGGGGGGGGGGGPP55Y7:                                                                                                        
                                                                                                                                                                                                                                   JGGGGGGGGGGGGGGGGGGGGGGGGPP55YJ^    7PGGGGGGGGGGGGGGGGGGGGGGGGPPP55YJ^                                                                                                       
                                                                                                                                                                                                                                  ?GGGGGGGGGGGGGGGGGGGGGGGGGPPP55YJ~  7PPPGGGGGGGGGGGGGGGGGGGGGGGGPP55YJ?:                                                                                                      
                                                                                                                                                                                                                                 7PPPPPPPGGGGGGGGGGGGGGGGGGGPPP55YJ?^7PPPPGGGGGGGGGGGGGGGGGGGGGGGGPPP5YYJ7.                                                                                                     
                                                                                                                                                                                                                                ~P5YJJ?JJY5PPGGGGGGGGGGGGGGGPPP555YJYPPP55555PPGGGGGGGGGGGGGGGGGGGPPP55YY?!.                                                                                                    
                                  -                                                                                                                                                                                             .55?^:..::^~7J5PPGGGGGGGGGGGGPPP555YJ555J7~~~~7J5PPGGGGGGGGGGGGGGGGPPP55YYJ7:                                                                                                    
                                                                                                                                                                                                                               :Y!:......~7!!~7YPPGGGGGGGGGGPPP555YY5J!:......:^?YPPGGGGGGGGGGGGGPPPP55YYJ?^                                                                                                    
                                                                                                                                                                                                                               ~!.......7PGG57^~J5PGGGGGGGGPPPP555YYJ~..... ....:!J5PGGGGGGGGGGGGPPPP55YYJ?~^~~^:...                                                                                            
                                                                                                                                                                                                                               !:......~PGGGGPJ:^?5PGGGGGGPPPPP555YJ7.  ...:7YYY7^~YPPGGGGGGGGGGGPPPP55YYJ?~!JYYJJJJJ?7!~~^:.                                                                                   
                                                                                                                                                                                                                              .!. .....^5GGGGPJ:.~YPPGGGGPPPPPP55YYJ^. .. .JPGGGPJ~75PPGGGGGGGGPPPPPP55YYJ?!~JYYYYYYYYYYYYJJJ?7~:                                                                               
                                                                                                                                                                                                                              .^.   ....?PGGG5!..:?5PGGPPPPPPP555YJ7.  .. ^5PPPPGP7~J5PGGGGGGGPPPPPP555YJJ?!^J555YYYYYYYYYYYJJJJJ?!^.                                                                           
                                                                                                                                                                                                                               ^.   .....755J!....~YPPPPPPPPPP555YJ7.   . .YPPPPPP?^7Y5PPGGGGGPPPPPP55YYJ?7~~Y5555555YYYYYYYYYYJJJJJJ7~.                                                                        
                                                                                                                                                                                                                               :.      . ..... ...^J5PPPPPPPP555YYJ!.     .:75PPPY!.^?5PPPGPPPPPPPP555YJJ?7^!55555555555YYYYYYYYYYYJJJJJ?!^.                                                                    
                                                                                                                                                                                                                               ..           ......^J5PPPPPPP555YYJ?!.       .:~!~:..:!Y5PPPPPPPPPP555YYJ?7!^75555555555YYYYYYYYYYYYYYYJJJJJJ7^.                                                                 
                                                                                                                                                                                                                               .:          .......~Y5PPPPPP555YYYJ?!.              ..~J5PPPPPPPPP5555YJJ?!~^J555YYYYYYYYYYYYYYYYYYYYYYYYJJJJJJ?!.                                                               
                                                                                                                                                                                                                                :.             ..:755PPPPP5555YYJ?7!:              ..~?55PPPPPPP555YYJJ?7!^~JYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJ???7^                                                              
                                                                                                                                                                                                                                 ^:           ..:!J55555555YYYJ??7?J~.             ..~?Y5PPPPP55555YYJ?7!~^7YYYYYJJJJJJJJJJJJJJYYYYYYYYYYJJJJ?????!.                                                            
                                                                                                                                                                                                                                  ^:.       ...^7JY5555555YYJJJYY5PGY:.            ..~?Y555555555YYJJ?7!~^!JYYYYYJJJJJJJJJJJJJJJJJJJYYYYYJJJJ??????!.                                                           
                                                                                                                                                                                                                                   ~!^....::^~!?JYY55YYYYYYY5PPPPPPPP?:.           .:!JY5555555YYJJ??7~^^!YYYYYYYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ?????7!:                                                          
                                                                                                                                                                                                                                    .~77!77??JJJJYYYYY55PPPPPPPPPPPPPPJ~.        ..:!?JY5555YYYJJJ?7!~^^~JYYYYYYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ????7777:                                                         
                                                                                                                                                                                                                                      .~77???JY5555PPPPPPPPPPPPPPPPPPPGPJ!^:.....:^!?JYYYYYYJJJJ?77~^^^!JYYYJJJJJJJJJJJJJJJJJJJJJ???JJJJJJJJJJJ????777!^                                                        
                                                                                                                                                                                                                                         :7J5PPPPPPPPPPPPPPPPPPPPPGGGGGGGGPP5?!~!77??JJJJJJJ???7!~^^^!JYYJJJJJJJJJJJJJJJJJJJJJJJ????????JJJJJJJ????7777!^                                                       
                                                                                                                                                                                                                                       .!Y5PPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGP5YJ????77777!~~^^^!?JYYYYJJJJJJJJJJJJJJJJJJJJJ?????????????????????7777!!:                                                      
                                                                                                                                                                                                                                     .!5PPPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGPPPPPPPP55YYJJJ???JJYYYYYYYJJJJJJJJJJJJJJJJJJJ????????????????????????7777!!^                                                      
                                                                                                                                                                                                                                    ~YPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPPPPPP5555YYYYYYJJJJJJJJJJJJJJJJ???????????????????????????77777!!~:                                                     
                                                                                                                                                                                                                                  ^YPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPPPP555555YYYYYYJJJJJJJJJJJJJJJJ?????????????777??????????777777!!~^                                                     
                                                                                                                                                                                                                                :J5PPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPPPP555555YYYYYJJJJJJJJJJJJJJJJJJ????????????777?????????7777777!!!^.                                                    
                                                                                                                                                                                                                              .75PPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPPPP555555YYYYYJJJJJJJJJJJJJJJJ?????????????77777????????7777777!!!^:                                                    
                                                                                                                             ..::^^^~~~!!!!!!!!!777777!!!!!~~~~^^^^:::...                                                    !5PPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPP5555555YYYYYJJJJJJJJJJJ?????????????????777777???????77777777!!!~^                                                    
                                                                                                                      .^!7?JYYY55555555YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJ??77!~^:.                                        ~YPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPP5555555YYYYYJJJJJJJJJJJ?J???????????????777777?????777777777!!!!~^.                                                   
                                                                                                                .:~7JYY55YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJ????7~^.                                  :JPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPP5P55555555YYYYYJJJJJJJJJ?????????????????777777????7??777777777!!!!!~.                                                   
                                                                                                            .~?Y55YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY55555YYYYYYYYYYYYYYYYYYJJJJJJ??????777!~:.                            .?PPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPP55555555555YYYYYJJJJJJJJJ????????????????777??????????777777777!!!!~~.                                                   
                                                                                                          ^J55YYYYJYYYYYYYYYYYYYYYYYYYYYY555555555555555555YYYYYYYYYYYYYYYJJJJJJ????7777!!~^:.                       .7PPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPP5555555555555YYYYYYJJJJJJJJ?????????????????????????????777777777!!!!~~^.                                                   
                                                                                         ...::^^~~!!7??JJJYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY55555555555555YYYYYYYYYYYYYYYJJJJJ????7777!!!~~^^:                     !5PPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPP555555555555555YYYYYYYJJJJJJ????????????????????????????????777777!!!!~~~^                                                    
                                                                       ....::^^~!!77??JJJYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY555555YYYYYYYYYYYYYYJJJJJJ?????7777!!!~~^:::                  ^YPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPP555555555555555YYYYYYYYYYJJJJJ???????????????????????????????77777!!!!~~~~^:                                                    
                                                       ...::^~~!7??JJYYYYY5555555YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY5Y5YYYYYYYYYYYYJJJJJJJJ????77777!!!~~^::^:               .YPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPP5555555555555555YYYYYYYYYYYYJJJJJJJJ???????????????????????????777777!!!!~~~~^^:                                                    
                                                 .^7JY555555555555555555YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJJ?????7777!!!~~~^::^^.             :5PPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPP555555555555555555YYYYYYYYYYYYYJJJJJJJJJJJJ??????????????????????7777777!!!!~~~~^^^.                                                    
                                            .~7J5PPPPPPP55555555555YYYYYYYYYYYYYYYYYYYYJJJJJJJJJJJJJJJJJJJJJJJJJJYYYYYYYYYYJJJYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJJJJ?????7777!!!!~~~^::^^:            ~PPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPP555555555555555555YYYYYYYJJJJJJJJJJJJJJJJJJJJ?????????????????????777777!!!!!~~~^^^^:                                                     
                                       .^7YPGGGGPPPP55555555555YYYYYYYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYYYYYYYYYYYYJJJJJJJJJJJJJ???????77777!!!~~~^^::^^^.          !PPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPP55555555555555555YYYYYYYYJJJJ??????JJJJJJJJJJJJ???????????????????7777777!!!!~~~~^^^^^                                                      
                                    .YPGGGGGGGPPPP55555555YYYYYYYYJJJJJJJJJJJJJJ???????????????JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ?????????77777!!!!~~~^^:^^^^.         ?PPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPP55555555555555YYYYYYYYYJJJJJ????JJJJJJJJJJJJJ?????????????????777777777!!!!!~~~~^^^^:                                                       
                                    YBGGGGGGGGPPP55555YYYYYYYJJJJJJJJJJJJJ??????????????????????????JJJJJJJJJJJ?????????????????????????????????JJJJJJJJJJJJJJJJJJJJJJ??????????7777!!!!~~~^^:^^^^.       .JPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPP5555555555555YYYYYYYJJJJJJJJJJJ???JJJJJJJJJJJJJJJ??????????????777777777!!!!!~~~~^^^^:.                                                        
                                   ?GGGGGGGGGPPP55YYYYYYJJJJJJJJJJJJJ??????????????????????????????????????????????????????????????????????????????????????JJJJJJJJJJJJJ????????7777!!!!~~~^^:^^^^:      .JPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPP5555555555555YYYYJJJJJ?????????JJJJJJ?JJJJJJJJJJJJJ????????????7777777777!!!!!!!~~~^^^^:.                                                          
                                  7GGGGGGGPPP555YYYJJJJJJJJJJJJJJ??????????????????????777?7??????????????7777777777777777777777777777777?7??????????????????JJJJJJJJJJJ????????7777!!!~~~~^^^^^^^:     ^555PPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPP55555555555555YYYJJ??77777777????JJJJJJJJJJJJJJJJJ?????????????777777777!!!!!!!!~~~~^^^^:.                                                            
                                  5GPPPPPP555YYYJJJJJJJJJJ???????????????????7777777777777777777777777777!!!!!!!!!!!~~~~~~~~~~~~~~!!!!!!!77777777777777777??????JJJJJJJJJJJ??????777!!!~~~^^^^^^^^:    ~5555PPPPGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPPP5555555555555YJ????777777????????JJJJJJJJJJJJJJJJJ???????????7777777777!!!!!!!!~~~^^^^^^:.                                                              
                                  :YP55555YYJJJJJJJJJJ????????????????????77777777777777!!!!!!!!!~~~~~~~~^^^^^^^^^^^^^^^^^^^^::^^^^^^^~~~~~~!!!!!!!!!77777777??????JJJJJJJJ??????7777!!~~~^^^^^^^^.   !5555PPPPPPPPPPPGGGGGGGGGGGGGGPPPPPPPPPPPPPPPPP555555555555YYYYJ?777?????JJJJJJJJJJJJJJJJJJJJJJJ????????????777777777!!!!!!!!~~~^^^^^^^^:                                                                 
                                   .JYYJJJ???????????????????????7777777777!!!!!!~~~~~~~^^^^^^^^^^^^::::::::::::::::::::::::::::::::::::::^^^^^^~~~~~!!!!7777777?????JJJJJJJJ????7777!!~~~^^^^^^^^.  ?5555PPPPPPPPPPPPPPPPPGGPPPPPPPPPPPPPPPPPPPPP555555555YYYYYJJJJJ??7???JJJJJJJJJJJJJJJJJJJJJJJJJ???????????777777777!!!!!!!!~~~^^^:^^^^:.                                                                   
                                    .7????????????????7777777777!!!!~~~~~~^^^^^^^^:^::::::::::::::::^^^^~~~~!!77~~~!!7??JJ??777!~~^^:::::::::::::^^~~!!77777777777??????JJJJJ?????777!!!~~^^^^^^^^  :555555PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP555555555YYYJJJJ?????????JJJJJJJJJJJJJJJJJJJJJJJJJJJ??????????77777777!!!!!!!!~~~^^^^^^^^::.                                                                      
                                      .::^~~~~~~~~~~~~~~~~~~~~^^^^^^^^:::::::::::::::::........     :JY555555P55J77?JJY55PPP555YJJ?77~^^::::::::^^~~!!777?????????????????JJJJ????7777!!~~^^^^^^^:   :JYY5555555555PPPPPPPPPPPPP55555555555555555YYYJJJ?????77777????JJJJJJJJJJJJJJJJJJJJJJJ?????????????777777777!!!!!!~~~^^^^^^^^::.                                                                          
                                                ......:::.................                           ~Y5555555555JJJYY5555P555YYJJJ???77!~~~~~~~~!!!!777???JJJJJJJJJJJJJJJJJJJ???7777!!~~^^^^^^^^     .?YYYYYYYYY5555555555555YYYYYYYYYYYJJJJJJ????????????JJJJJJJJJJJJJJJJJJJJJJJ???????????????777777777777!!!!!!!~~~^^^^^^^::..                                                                              
                                                                                                      ~YY555555555YYY55555555YYYJJJ??77777777!!7777???????JJJJJJJYYYYYYYYYYYJJ???777!!~~^^^^^^^^:       ?55YYYYYYYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ?????????????????7777777777!!!!!!!!!~~~~~~^^^^^^^::.                                                                                   
                                                                                                       !JYYYY55555YYYYYYYYYYYYJJJJ???77!?5Y?7777?????JJJJJJJYYYYYYYYYYYYYYYYJJ???77!!~~^^^^^^^^^         ^J5555YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJJ????????777777777777777!!!!!!!!~~~~~~~^^^^^^^^^^::..                                                                                       
                                                                                                        !JJYYYYYYYYYYYYYYYJJJJJJ????77!!J5JJJJJJJJJJJJJYYYYYYYYYYY5555555YYYJJ???77!~~^^^^^^^^^.           .!Y55555555555555555555555555555555YYYYYYYYYYJJJJJJJJJ??????77777777777!!!!!!!!!~~~~~~~^^^^^^^^::::^^::..                                                                                            
                                                                                                         ^?JJJJJJJJJJJJJJJJJJJ???777!~!Y5YYYYYYYYYYYYYYYYY555555555555555YYYJJ???77!!~^^^^^^^^:               ~J5555555555555555555555555555YYYYYYYYYYJJJJJJ??????77777!!!!!!!!!!!~~~~~~~~^^^^^^^^:::::^^::::..                                                                                                 
                                                                                                          .7????JJJJJJJJJ?????77!!~~^7PP555555555555555555555555555555555YYYJJJ??77!!~^^^^^^^:                  .!JYYYY555555555555555YYYYYYYYYYJJJJJJJJ??????7777!!!!!~~~~~~~~~~^^^^^^^^^::::::::::....                                                                                                        
                                                                                                            :~!77????????777!!!~^^^~JGPP555555555555555555PPPPPPPPPPPP555YYYJJJ??77!!~^^^^^^:                      :~7JJJJJYYYYYJYYJJJJJJJJJJJJJJ??????7777777!!!!~~~~^^^^^^^^^^^:::::::::....                                                                                                                  
                                                                                                                ..::^^~~~~~~^^^:::!5GGPPPPPPPPPPPPPPPPPPPPPPPPPPPP555555YYYYJJJ??77!~~^^^^^:                          .^!7??????????????7?7777777777777!!!!!!~~~~^^^^^^::::::::.....                                                                                                                            
                                                                                                                        ..::^!!7?J5PPPPPPPPPPPPPPPPPPPPPPPPPP5555555YYYYJJJJ??777!~~^^^^^^.                              .:^~!!!!!!!!!~~~~~~~~~~~~~~~~^^^^^^^^^::::.....                                                                                                                                        
                                                                                                                             .!JYYYY555PPPPP555555555555555555YYYYYJJJJ???777!!~~^^^^^^^:                                    .:::::::::::::::::::::.......                                                                                                                                                      
                                                                                                                               .~7??JJJYYYYYYYYYYYYYYYYYYYJJJJJJJJ????777!!~~^^^^^^^^^^.                                         ......                                                                                                                                                                         
                                                                                                                                  ..^~!7??JJJ?????????????777777777!!!~~~^^^:::^:::::.                                                                                                                                                                                                                          
                                                                                                                                        ..:^~!!!!!!~!~~~~~~~~^^^^^^^::::......                                                                                                                                                                                                                                  
*/