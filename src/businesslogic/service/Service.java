package businesslogic.service;

import businesslogic.menu.Menu;

public class Service {

    private Menu menuInUse;

    public Service(Menu menu){ // TODO
        this.menuInUse = menu;
    }
    public Menu getMenuInUse() {
        return menuInUse;
    }
}
