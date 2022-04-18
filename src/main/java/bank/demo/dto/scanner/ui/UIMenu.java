package bank.demo.dto.scanner.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

 //эта анотация, это бин, которая присоединяет все бины через скан и конфигурацию в одно
@Component
public class UIMenu {


    @Autowired //пишется только тогда, когда нет конструктора, или нет файла в конструторе
    private final List<UIAction> uiActions;

     public UIMenu(List<UIAction> uiActions) {
         this.uiActions = uiActions;
     }

//     public UIMenu(List<UIAction> uiActions) {// UIMenu(@Qualifier("withoutExit") List<UIAction> uiActions)
//        // здесь через @Qualifier мы пишим в скобках
//        // название бина, и через этот бин мы вызываем лист с новым меню
//        this.uiActions = uiActions;
//    }
    public void startUI(){
        while (true) {
            try {
                System.out.println("Please make your choice: ");
                for (int i = 0; i < uiActions.size(); i++) {
                    var uiAction = uiActions.get(i);
                    System.out.println(i + 1 +  ": " + uiAction.getActionName());
                }
                Scanner scanner = new Scanner(System.in);
                int userInput = scanner.nextInt();

                uiActions.get(userInput - 1).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
