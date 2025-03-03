package view;

import controller.Features;
import java.awt.Font;
import java.util.SortedMap;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This is a class that creates a navigation bar with an action listener.
 */
class NavigationBar extends JMenuBar {


  /**
   * Constructs a new navigation bar with multiple menus and menu item(s) for these menus.
   *
   * @param menus a sorted map with a String that maps to a String array.
   */
  NavigationBar(SortedMap<String, String[]> menus) {
    super();
    Font f = new Font(this.getFont().getFontName(), this.getFont().getStyle(), 20);
    menus.forEach((menuName, menuItemNames) -> {

      JMenu menu = new JMenu(menuName);
      for (String menuItemName : menuItemNames) {
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.setFont(f);
        menuItem.setActionCommand(menuItemName);
        menu.add(menuItem);
      }
      this.add(menu);
      menu.setFont(f);

    });

    this.setSize(500, 40);
    this.setVisible(true);
  }

  /**
   * Adds an action listener for all menu items in the menu.
   *
   * @param feature the features
   */
  void addActionListener(Features feature) {
    for (int i = 0; i < getMenuCount(); i++) {
      for (int j = 0; j < getMenu(i).getItemCount(); j++) {
        getMenu(i).getItem(j)
            .addActionListener(evt -> feature.handleMenuItemClick(evt.getActionCommand()));

      }
    }
  }


}
