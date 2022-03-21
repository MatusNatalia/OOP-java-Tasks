package ru.nsu.ccfit.matus.task3;


public class Controller {

    public Controller(){
    }

    public void execute(String[] step, Model model, View view) throws UnknownCommandException, OutOfFieldException {
        if(step[0].equals("quit")){
            view.quit();
        }
        else if(step[0].equals("new")){
            model.clear();
            view.clear(model);
        }
        else if(step[0].equals("size")){
            model.changeSize(Integer.parseInt(step[1]));
            view.changeSize(model);
        }
        else if(step[0].equals("about")){
            view.showAbout();
        }
        else if(step[0].equals("score")){
            view.showScore(model);
        }
        else if(model.isEndGame()){
            model.clear();
            view.clear(model);
        }
        else{
            if(step.length != 2){
                throw new UnknownCommandException();
            }
            try {
                int x = Integer.parseInt(step[0]);
                int y = Integer.parseInt(step[1]);
                model.update(x, y);
                view.update(model);
            } catch (NumberFormatException e){
                throw new UnknownCommandException();
            } catch (OutOfFieldException e){
                throw e;
            }
        }
    }
}

