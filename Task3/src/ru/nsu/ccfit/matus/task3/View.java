package ru.nsu.ccfit.matus.task3;

interface View {
    public String[] getNextStep() throws InterruptedException;
    public void update(Model model);
    public void clear(Model model);
    public void quit();
    public void start();
    public void showAbout();
    public void showScore(Model model);
    public void showException(Exception e);
    void changeSize(Model model);
}
