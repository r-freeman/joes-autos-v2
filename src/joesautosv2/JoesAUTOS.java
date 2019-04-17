/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JoesAUTOS {
    private static Admin admin;
    private static RegionalSalesManager regionalSalesManager;
    private static SalesManager salesManager;
    private static Salesman salesman;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();

        int opt = 0;

        do {
            System.out.println("\n******** Joe's AUTOS v2 ********");
            System.out.println("1. Log in");
            System.out.println("2. Exit");
            System.out.println("\nEnter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        employeeLogin(keyboard, model);
                        break;
                    }

                    case 2: {
                        break;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 2);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }

    /**
     * We need to find the role of the employee and then create an instance of one of the sub classes
     *
     * @param keyboard
     * @param model
     */
    private static void employeeLogin(Scanner keyboard, Model model) {
        EmployeeRole employeeRole;
        int employeeId;

        do {
            try {
                System.out.println("\nEnter Employee ID : ");
                String line = keyboard.nextLine();
                employeeId = Integer.parseInt(line);

                employeeRole = getEmployeeRole(model, employeeId);

                System.out.println("Enter password : ********");

                switch (employeeRole.getRoleId()) {
                    case 1: {
                        // admin
                        setAdmin(getAdminByEmployeeId(model, employeeId));
                        adminMenu(keyboard, model);
                        break;
                    }

                    case 2: {
                        // regional sales manager
                        setRegionalSalesManager(getRegionalSalesManagerByEmployeeId(model, employeeId));
                        regionalSalesManagerMenu(keyboard, model);
                        break;
                    }

                    case 3: {
                        // sales manager
                        setSalesManager(getSalesManagerByEmployeeId(model, employeeId));
                        salesManagerMenu(keyboard, model);
                        break;
                    }

                    case 4: {
                        // salesman
                        setSalesman(getSalesmanByEmployeeId(model, employeeId));
                        salesmanMenu(keyboard, model);
                        break;
                    }
                }
            } catch (NumberFormatException | NullPointerException ex) {
                System.out.println("\nEmployee ID not recognised.");
            }
        }
        while (true);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void adminMenu(Scanner keyboard, Model model) {
        int opt = 0;

        adminMenu:
        do {
            System.out.println("\n******** ADMIN MENU ********");
            System.out.println("1. Manage employees");
            System.out.println("2. Log out");
            System.out.println("\nWelcome back " + admin.getFirstName() + ", enter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        manageEmployees(keyboard, model);
                        break adminMenu;
                    }

                    case 2: {
                        break;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 2);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void regionalSalesManagerMenu(Scanner keyboard, Model model) {
        int opt = 0;

        regionSalesManagerMenu:
        do {
            System.out.println("\n******** REGIONAL SALES MANAGER MENU ********");
            System.out.println("1. Manage sales managers");
            System.out.println("2. Log out");
            System.out.println("\nWelcome back " + regionalSalesManager.getFirstName() + ", enter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        manageSalesManagers(keyboard, model);
                        break regionSalesManagerMenu;
                    }

                    case 2: {
                        break;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 2);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void manageSalesManagers(Scanner keyboard, Model model) {
        int opt = 0;

        // label so we can break out of this loop
        manageSalesManagers:
        do {
            System.out.println("\n******** MANAGE SALES MANAGERS ********");
            System.out.println("1. View my sales managers");
            System.out.println("2. Assign myself to sales managers");
            System.out.println("3. Add new sales manager");
            System.out.println("4. Edit my sales managers");
            System.out.println("5. Go back");
            System.out.println("\nEnter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        viewSalesManagers();
                        break;
                    }

                    case 2: {
                        assignRegionalSalesManager(keyboard, model, regionalSalesManager);
                        // we need this line to pull the new sales manager list from db, very important!
                        setRegionalSalesManager(getRegionalSalesManagerByEmployeeId(model, regionalSalesManager.getEmployeeId()));
                        break;
                    }

                    case 3: {
                        // add new sales manager
                        EmployeeRole employeeRole = getEmployeeRoleById(model, 3);
                        readEmployee(keyboard, model, employeeRole);
                        break;
                    }

                    case 4: {
                        // edit existing sales manager
                        editSalesManager(keyboard, model);
                        break;
                    }

                    case 5: {
                        regionalSalesManagerMenu(keyboard, model);
                        break manageSalesManagers;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 5);
    }

    /**
     *
     */
    private static void viewSalesManagers() {
        regionalSalesManager.printSalesManagers();
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void editSalesManager(Scanner keyboard, Model model) {
        List<SalesManager> salesManagers = regionalSalesManager.getSalesManagers();
        int employeeId;

        System.out.println("\nEnter employee ID of sales manager : ");
        String line = keyboard.nextLine();
        employeeId = Integer.parseInt(line);

        for (SalesManager sm : salesManagers) {
            if (employeeId == sm.getEmployeeId()) {
                updateEmployee(keyboard, model, null, null, sm, null);
                System.out.println("\nEmployee updated");
                return;
            }
        }
        System.out.println("\nCould not find sales manager");
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void salesManagerMenu(Scanner keyboard, Model model) {
        int opt = 0;

        salesManagerMenu:
        do {
            System.out.println("\n******** SALES MANAGER MENU ********");
            System.out.println("1. Manage salesmen");
            System.out.println("2. Manage motorbikes");
            System.out.println("3. Manage sales");
            System.out.println("4. View total sum of sales");
            System.out.println("5. View total number of motorbikes sold");
            System.out.println("6. Log out");
            System.out.println("\nWelcome back " + salesManager.getFirstName() + ", enter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        manageSalesmen(keyboard, model);
                        break salesManagerMenu;
                    }

                    case 2: {
                        manageMotorbikes(keyboard, model);
                        break salesManagerMenu;
                    }

                    case 3: {
                        manageSales(keyboard, model);
                        break salesManagerMenu;
                    }

                    case 4: {
                        System.out.println("\nTotal sum of sales : €" + viewTotalSumSales(model));
                        break;
                    }

                    case 5: {
                        System.out.println("\nTotal number of motorbikes sold : " + viewTotalNumMotorbikesSold(model));
                        break;
                    }

                    case 6: {
                        break;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 6);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }


    /**
     * @param keyboard
     * @param model
     * @throws InvalidOptionException
     */
    private static void manageSales(Scanner keyboard, Model model) {
        int opt = 0;

        manageSales:
        do {
            System.out.println("\n******** MANAGE SALES ********");
            System.out.println("1. View all sales");
            System.out.println("2. View sales by status");
            System.out.println("3. Go back");
            System.out.println("\nEnter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch(opt){
                    case 1: {
                        // lists all sales
                        viewSales(model, "", null);
                        break manageSales;
                    }

                    case 2: {
                        // view sales by status
                        System.out.println("Enter status (complete or void) : ");
                        String status = keyboard.nextLine();
                        viewSales(model, status, null);
                        break manageSales;
                    }

                    case 3: {
                        salesManagerMenu(keyboard, model);
                        break manageSales;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 3);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void manageSalesmen(Scanner keyboard, Model model) {
        int opt = 0;

        // label so we can break out of this loop
        manageSalesmen:
        do {
            System.out.println("\n******** MANAGE SALESMEN ********");
            System.out.println("1. View my salesmen");
            System.out.println("2. Assign myself to salesmen");
            System.out.println("3. Add new salesman");
            System.out.println("4. Edit my salesmen");
            System.out.println("5. Go back");
            System.out.println("\nEnter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        viewSalesManagersSalesmen();
                        break;
                    }

                    case 2: {
                        assignSalesManager(keyboard, model, salesManager);
                        // we need this line to pull the new salesmen list from db, very important!
                        setSalesManager(getSalesManagerByEmployeeId(model, salesManager.getEmployeeId()));
                        break;
                    }

                    case 3: {
                        // add new salesman
                        EmployeeRole employeeRole = getEmployeeRoleById(model, 4);
                        readEmployee(keyboard, model, employeeRole);
                        break;
                    }

                    case 4: {
                        // edit existing salesman
                        editSalesman(keyboard, model);
                        break;
                    }

                    case 5: {
                        salesManagerMenu(keyboard, model);
                        break manageSalesmen;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 5);
    }

    private static void manageMotorbikes(Scanner keyboard, Model model) {
        int opt = 0;

        do {
            System.out.println("\n******** MANAGE MOTORBIKES ********");
            System.out.println("1. Add new motorbike");
            System.out.println("2. Edit motorbike");
            System.out.println("3. Delete motorbike");
            System.out.println("4. View motorbikes by frame");
            System.out.println("5. View all motorbikes");
            System.out.println("6. Go back");
            System.out.println("\nEnter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        // add new motorbike
                        addMotorbike(keyboard, model);
                        break;
                    }

                    case 2: {
                        // edit motorbike
                        editMotorbike(keyboard, model);
                        break;
                    }

                    case 3: {
                        // delete motorbike
                        deleteMotorbike(keyboard, model);
                        break;
                    }

                    case 4: {
                        // view motorbikes by frame
                        viewMotorbikesByFrame(keyboard, model);
                        break;
                    }

                    case 5: {
                        // view all motorbikes
                        viewMotorbikes(keyboard, model);
                        break;
                    }

                    case 6: {
                        salesManagerMenu(keyboard, model);
                        break;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }

                // back to manage motorbikes loop
                manageMotorbikes(keyboard, model);
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 6);
    }

    /**
     * @param keyboard
     * @param model
     */
    public static void addMotorbike(Scanner keyboard, Model model) {
        MotorbikeFrame motorbikeFrame = null;

        do {
            try {
                System.out.println("\nWhat kind of motorbike?");
                listMotorbikeFrames(model);
                System.out.println("\nEnter number : ");

                // make sure the motorbike frame id is valid
                motorbikeFrame = validateMotorbikeFrame(keyboard, model);

                if (motorbikeFrame != null) {
                    readMotorbike(keyboard, model, motorbikeFrame);
                    // back to manage motorbikes loop
                    manageMotorbikes(keyboard, model);
                } else {
                    throw new InvalidFrameException();
                }
            } catch (NumberFormatException | InvalidFrameException ex) {
                System.out.println("\nInvalid motorbike frame, try again.");
            }
        } while (motorbikeFrame != null);
    }

    /**
     *
     */
    private static void editSalesman(Scanner keyboard, Model model) {
        List<Salesman> salesmen = salesManager.getSalesmen();
        int employeeId;

        System.out.println("\nEnter employee ID of salesman : ");
        String line = keyboard.nextLine();
        employeeId = Integer.parseInt(line);

        for (Salesman s : salesmen) {
            if (employeeId == s.getEmployeeId()) {
                updateEmployee(keyboard, model, null, null, null, s);
                System.out.println("\nEmployee updated");
                return;
            }
        }
        System.out.println("\nCould not find salesman");
    }

    /**
     *
     */
    private static void viewSalesManagersSalesmen() {
        salesManager.printSalesmen();
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void salesmanMenu(Scanner keyboard, Model model) {
        int opt;

        salesmanMenu:
        do {
            System.out.println("\n******** SALESMAN MENU ********");
            System.out.println("1. View all motorbikes");
            System.out.println("2. View motorbikes by frame");
            System.out.println("3. Create new sale");
            System.out.println("4. View my sales");
            System.out.println("5. Log out");
            System.out.println("\nWelcome back " + salesman.getFirstName() + ", enter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        viewMotorbikes(keyboard, model);
                        break;
                    }

                    case 2: {
                        viewMotorbikesByFrame(keyboard, model);
                        break;
                    }

                    case 3: {
                        createSale(keyboard, model, salesman);
                        break;
                    }

                    case 4: {
                        viewSales(model, "complete", salesman);
                        break;
                    }

                    case 5: {
                        break salesmanMenu;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }

                salesmanMenu(keyboard, model);
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (true);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }


    /**
     * @param model
     * @param status
     * @param salesman
     */
    private static void viewSales(Model model, String status, Salesman salesman) {
        List<Sale> sales;
        Motorbike motorbike;

        if (salesman == null && status.equals("")) {
            // list all sales
            sales = getSales(model);
        } else if (salesman == null && !"".equals(status)) {
            // list sales by status, either complete or void
            status = status.toLowerCase();
            sales = getSalesByStatus(model, status);
        } else {
            // list sales by a particular salesman
            sales = getSalesBySalesmanId(model, salesman.getSalesmanId());
        }

        // did db return any sales?
        if (sales.size() > 0) {
            for (Sale sale : sales) {
                // step through each sale in sales array
                motorbike = getMotorbikeById(model, sale.getMotorbikeId());

                sale.printDetails();

                if (salesman != null) {
                    salesman.printDetails();
                    System.out.println("Commission @ " + salesman.getCommissionRate() * 1 + "% : €" + sale.getCommission());
                }

                motorbike.printDetails();
            }
        } else {
            System.out.println("\nNo sales found");
        }
    }

    /**
     * @param keyboard
     * @param model
     * @param salesman
     */
    private static void createSale(Scanner keyboard, Model model, Salesman salesman) {
        final double VAT = 0.23;
        double total, commission;
        int motorbikeId, stock;

        String status;
        Motorbike motorbike;
        Sale sale;

        viewMotorbikes(keyboard, model);
        System.out.println("\nEnter motorbike ID : ");
        motorbikeId = keyboard.nextInt();
        keyboard.nextLine();

        motorbike = getMotorbikeById(model, motorbikeId);

        if (motorbike != null) {
            stock = motorbike.getStock();
            // can't sell if the motorbike isn't in stock!
            if (stock > 0) {
                // let the salesman calculate sales total incl VAT and commission
                total = salesman.getSaleTotal(VAT, motorbike.getPrice());
                commission = salesman.getSaleCommission(motorbike.getPrice());

                System.out.println("\n******** SALE CONFIRMATION ********");
                System.out.println("\nMake : " + motorbike.getMake());
                System.out.println("Model : " + motorbike.getModel());
                System.out.println("Engine : " + motorbike.getEngine());
                System.out.println("Frame : " + motorbike.getFrameName());
                System.out.println("Colour : " + motorbike.getColour());
                System.out.println("Year : " + motorbike.getYear());
                System.out.println("Price : €" + motorbike.getPrice());
                System.out.println("Stock : " + motorbike.getStock());
                System.out.println("\nTotal Incl VAT @ " + VAT * 100 + "% : €" + total);

                System.out.println("\nSalesman : " + salesman.getFirstName() + " " + salesman.getLastName());
                System.out.println("Sale commission : €" + commission);

                System.out.println("\nConfirm sale [y] : ");
                String confirm = keyboard.nextLine();
                confirm = confirm.toLowerCase();

                if (confirm.equals("") || confirm.equals("y")) {
                    status = "complete";
                    // set Motorbike -1 stock and store in db
                    stock--;
                    motorbike.setStock(stock);
                    _updateMotorbike(model, motorbike);
                } else {
                    // sale was cancelled
                    status = "void";
                }

                // create instance of sale model and store in db
                sale = new Sale(status, total, commission, salesman.getSalesmanId(), motorbike.getMotorbikeId());
                addSale(model, sale);

                System.out.println("\nSale " + status);
            } else {
                System.out.println("\nMotorbike is out of stock!");
            }
        } else {
            System.out.println("\nMotorbike not found");
        }
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void manageEmployees(Scanner keyboard, Model model) {
        int opt = 0;

        // label so we can break out of this loop
        manageEmployees:
        do {
            System.out.println("\n******** MANAGE EMPLOYEES ********");
            System.out.println("1. Add new employee");
            System.out.println("2. Edit employee");
            System.out.println("3. Delete employee");
            System.out.println("4. View employees by role");
            System.out.println("5. Go back");
            System.out.println("\nEnter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        // add employee
                        addEmployee(keyboard, model);
                        break manageEmployees;
                    }

                    case 2: {
                        editEmployee(keyboard, model);
                        break manageEmployees;
                    }

                    case 3: {
                        deleteEmployee(keyboard, model);
                        break manageEmployees;
                    }

                    case 4: {
                        viewEmployeesByRole(keyboard, model);
                        break manageEmployees;
                    }

                    case 5: {
                        adminMenu(keyboard, model);
                        break manageEmployees;
                    }

                    default: {
                        throw new InvalidOptionException();
                    }
                }
            } catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 5);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void deleteEmployee(Scanner keyboard, Model model) {
        Admin _admin = null;
        RegionalSalesManager _regionalSalesManager = null;
        SalesManager _salesManager = null;
        Salesman _salesman = null;

        String confirm;
        boolean employeeIsValid = false;
        EmployeeRole employeeRole;
        int employeeId;

        do {
            try {
                System.out.println("\nEnter the employee's ID : ");
                String line = keyboard.nextLine();
                employeeId = Integer.parseInt(line);

                // make sure employee id is valid
                if (validateEmployeeId(model, employeeId) <= 0) {
                    throw new InvalidEmployeeException("\nInvalid employee, try again.");
                }

                employeeRole = getEmployeeRole(model, employeeId);

                switch (employeeRole.getRoleId()) {
                    case 1: {
                        // admin is logged in and trying to delete themselves
                        if (admin.getEmployeeId() == employeeId) {
                            throw new InvalidEmployeeException("\nCannot delete yourself, try again.");
                        }

                        _admin = getAdminByEmployeeId(model, employeeId);
                        _admin.printDetails();

                        break;
                    }

                    case 2: {
                        // regional sales manager
                        _regionalSalesManager = getRegionalSalesManagerByEmployeeId(model, employeeId);
                        _regionalSalesManager.printDetails();

                        break;
                    }

                    case 3: {
                        // sales manager
                        _salesManager = getSalesManagerByEmployeeId(model, employeeId);
                        _salesManager.printDetails();

                        break;
                    }

                    case 4: {
                        // salesman
                        _salesman = getSalesmanByEmployeeId(model, employeeId);
                        _salesman.printDetails();

                        break;
                    }
                }

                employeeIsValid = true;

                System.out.println("\nDelete this employee? [y] : ");
                confirm = keyboard.nextLine();
                confirm = confirm.toLowerCase();

                if (confirm.equals("") || confirm.equals("y")) {
                    if (_admin != null) {
                        deleteAdmin(model, _admin);
                    } else if (_regionalSalesManager != null) {
                        deleteRegionalSalesManager(model, _regionalSalesManager);
                    } else if (_salesManager != null) {
                        deleteSalesManager(model, _salesManager);
                    } else if (_salesman != null) {
                        deleteSalesman(model, _salesman);
                    }
                    System.out.println("\nEmployee deleted");
                }

                // back to manage employees
                manageEmployees(keyboard, model);

            } catch (InvalidEmployeeException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!employeeIsValid);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void deleteMotorbike(Scanner keyboard, Model model) {
        Motorbike motorbike;
        String confirm;
        boolean motorbikeIsValid = false;
        int motorbikeId;

        do {
            try {
                System.out.println("\nEnter the motorbike ID : ");
                String line = keyboard.nextLine();
                motorbikeId = Integer.parseInt(line);

                // make sure motorbike is is valid
                if (validateMotorbikeId(model, motorbikeId) <= 0) {
                    throw new InvalidMotorbikeException("\nInvalid motorbike, try again.");
                }

                motorbike = getMotorbikeById(model, motorbikeId);
                motorbike.printDetails();

                motorbikeIsValid = true;

                System.out.println("\nDelete this motorbike? [y] : ");
                confirm = keyboard.nextLine();
                confirm = confirm.toLowerCase();

                if (confirm.equals("") || confirm.equals("y")) {
                    _deleteMotorbike(model, motorbike);
                    System.out.println("\nMotorbike deleted");
                }

                // back to manage employees
                manageEmployees(keyboard, model);

            } catch (InvalidMotorbikeException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!motorbikeIsValid);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void viewEmployeesByRole(Scanner keyboard, Model model) {
        EmployeeRole employeeRole = null;

        do {
            try {
                System.out.println("\nEmployee roles");
                listEmployeeRoles(model);
                System.out.println("\nEnter number : ");
                employeeRole = validateEmployeeRole(keyboard, model);

                if (employeeRole != null) {
                    switch (employeeRole.getRoleId()) {
                        case 1: {
                            List<Admin> admins = getAdmins(model);
                            for (Admin a : admins) {
                                a.printDetails();
                            }
                            break;
                        }

                        case 2: {
                            List<RegionalSalesManager> regionalSalesManagers = getRegionalSalesManagers(model);
                            for (RegionalSalesManager rsm : regionalSalesManagers) {
                                rsm.printDetails();
                            }
                            break;
                        }

                        case 3: {
                            List<SalesManager> salesManagers = getSalesManagers(model, false);
                            for (SalesManager sm : salesManagers) {
                                sm.printDetails();
                            }
                            break;
                        }

                        case 4: {
                            List<Salesman> salesmen = getSalesmen(model, false);
                            for (Salesman s : salesmen) {
                                s.printDetails();
                            }
                            break;
                        }

                        default: {
                            throw new InvalidRoleException();
                        }
                    }

                    // back to manage employees loop
                    manageEmployees(keyboard, model);
                } else {
                    throw new InvalidRoleException();
                }
            } catch (NumberFormatException | InvalidRoleException ex) {
                System.out.println("\nInvalid employee role, try again.");
            }
        } while (employeeRole == null);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void viewMotorbikesByFrame(Scanner keyboard, Model model) {
        MotorbikeFrame motorbikeFrame = null;

        do {
            try {
                System.out.println("\nMotorbike frames");
                listMotorbikeFrames(model);
                System.out.println("\nEnter number : ");
                motorbikeFrame = validateMotorbikeFrame(keyboard, model);

                if (motorbikeFrame != null) {
                    List<Motorbike> motorbikes = getMotorbikesByFrame(model, motorbikeFrame.getFrameId());
                    if (motorbikes.size() > 0) {
                        for (Motorbike m : motorbikes) {
                            m.printDetails();
                        }
                    } else {
                        System.out.println("\nNo motorbikes with that frame");
                    }
                } else {
                    throw new InvalidFrameException();
                }
            } catch (NumberFormatException | InvalidFrameException ex) {
                System.out.println("\nInvalid motorbike frame, try again.");
            }
        } while (motorbikeFrame == null);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void viewMotorbikes(Scanner keyboard, Model model) {
        List<Motorbike> motorbikes = getMotorbikes(model);
        for (Motorbike m : motorbikes) {
            m.printDetails();
        }
    }

    /**
     * Small function for listing all employee roles
     *
     * @param model
     */
    private static void listEmployeeRoles(Model model) {
        List<EmployeeRole> employeeRoles;
        employeeRoles = getEmployeeRoles(model);
        for (EmployeeRole er : employeeRoles) er.printDetails();
    }

    /**
     * @param model
     */
    private static void listMotorbikeFrames(Model model) {
        List<MotorbikeFrame> motorbikeFrames;
        motorbikeFrames = getMotorbikeFrames(model);
        for (MotorbikeFrame mf : motorbikeFrames) mf.printDetails();
    }

    /**
     * @param keyboard
     * @param model
     * @return
     */
    private static EmployeeRole validateEmployeeRole(Scanner keyboard, Model model) {
        String line = keyboard.nextLine();
        int roleId = Integer.parseInt(line);
        EmployeeRole employeeRole = null;

        if (Integer.class.isInstance(roleId)) {
            employeeRole = getEmployeeRoleById(model, roleId);
        }

        return employeeRole;
    }

    /**
     * @param keyboard
     * @param model
     * @return
     */
    private static MotorbikeFrame validateMotorbikeFrame(Scanner keyboard, Model model) {
        String line = keyboard.nextLine();
        int frameId = Integer.parseInt(line);
        MotorbikeFrame motorbikeFrame = null;

        if (Integer.class.isInstance(frameId)) {
            motorbikeFrame = getMotorbikeFrameById(model, frameId);
        }

        return motorbikeFrame;
    }


    /**
     * @param keyboard
     * @param model
     */
    public static void addEmployee(Scanner keyboard, Model model) {
        EmployeeRole employeeRole = null;

        do {
            try {
                System.out.println("\nWhat is the employee's role?");
                listEmployeeRoles(model);
                System.out.println("\nEnter number : ");

                // make sure the employeeRole id is valid
                employeeRole = validateEmployeeRole(keyboard, model);

                if (employeeRole != null) {
                    readEmployee(keyboard, model, employeeRole);
                    // back to manage employees loop
                    manageEmployees(keyboard, model);
                } else {
                    throw new InvalidRoleException();
                }

            } catch (NumberFormatException | InvalidRoleException ex) {
                System.out.println("\nInvalid employee role, try again.");
            }
        } while (employeeRole == null);
    }

    /**
     * @param keyboard
     * @param model
     * @param employeeRole
     */
    private static void readEmployee(Scanner keyboard, Model model, EmployeeRole employeeRole) {
        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary, quarterlyBonus, commissionRate;

        try {
            System.out.println("\nEnter first name : ");
            firstName = keyboard.nextLine();

            System.out.println("Enter last name : ");
            lastName = keyboard.nextLine();

            System.out.println("Enter email address : ");
            email = keyboard.nextLine();

            System.out.println("Enter telephone : ");
            telephone = keyboard.nextLine();

            System.out.println("Enter address : ");
            address = keyboard.nextLine();

            System.out.println("Enter town : ");
            town = keyboard.nextLine();

            System.out.println("Enter city : ");
            city = keyboard.nextLine();

            System.out.println("Enter country : ");
            country = keyboard.nextLine();

            System.out.println("Enter post code : ");
            postCode = keyboard.nextLine();

            System.out.println("Enter salary : ");
            salary = keyboard.nextDouble();
            keyboard.nextLine();

            switch (employeeRole.getRoleId()) {
                case 1: {
                    Admin _admin = new Admin(firstName, lastName, email, telephone, address,
                            town, city, country, postCode, salary, employeeRole.getRoleId()
                    );
                    addAdmin(model, _admin);
                    break;
                }

                case 2: {
                    RegionalSalesManager _regionalSalesManager = new RegionalSalesManager(
                            firstName, lastName, email, telephone, address, town, city,
                            country, postCode, salary, employeeRole.getRoleId()
                    );
                    addRegionalSalesManager(model, _regionalSalesManager);
                    break;
                }

                case 3: {
                    System.out.println("Enter quarterly bonus : ");
                    quarterlyBonus = keyboard.nextDouble();
                    keyboard.nextLine();

                    SalesManager _salesManager = new SalesManager(firstName, lastName, email, telephone, address,
                            town, city, country, postCode, salary, employeeRole.getRoleId(), quarterlyBonus);
                    addSalesManager(model, _salesManager);
                    break;
                }

                case 4: {
                    System.out.println("Enter commission rate : ");
                    commissionRate = keyboard.nextDouble();
                    keyboard.nextLine();

                    Salesman _salesman = new Salesman(firstName, lastName, email, telephone, address,
                            town, city, country, postCode, salary, employeeRole.getRoleId(), commissionRate);

                    addSalesman(model, _salesman);
                    break;
                }

                default: {
                    throw new InvalidRoleException();
                }
            }

            System.out.println("\nEmployee added");
        } catch (NumberFormatException | InvalidRoleException ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    /**
     * @param keyboard
     * @param model
     * @param motorbikeFrame
     */
    private static void readMotorbike(Scanner keyboard, Model model, MotorbikeFrame motorbikeFrame) {
        String make, _model, engine, colour;
        double price;
        int year, stock;

        try {
            System.out.println("\nEnter make : ");
            make = keyboard.nextLine();

            System.out.println("Enter model : ");
            _model = keyboard.nextLine();

            System.out.println("Enter engine : ");
            engine = keyboard.nextLine();

            System.out.println("Enter colour : ");
            colour = keyboard.nextLine();

            System.out.println("Enter year : ");
            year = keyboard.nextInt();

            System.out.println("Enter stock : ");
            stock = keyboard.nextInt();

            System.out.println("Enter price : ");
            price = keyboard.nextDouble();
            keyboard.nextLine();

            Motorbike motorbike = new Motorbike(make, _model, engine, colour, year, price, stock, motorbikeFrame.getFrameId());
            storeMotorbike(model, motorbike);

            System.out.println("\nMotorbike added");
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void editEmployee(Scanner keyboard, Model model) {
        boolean employeeIsValid = false;
        int employeeId;
        EmployeeRole employeeRole = null;

        do {
            try {
                System.out.println("\nWhat is the employee's role?");
                listEmployeeRoles(model);
                System.out.println("\nEnter number : ");
                // make sure the employeeRole id is valid
                employeeRole = validateEmployeeRole(keyboard, model);

                System.out.println("\nEnter the employee's ID : ");
                String line = keyboard.nextLine();
                employeeId = Integer.parseInt(line);

                // validate employee id
                if (validateEmployeeId(model, employeeId) <= 0) {
                    throw new InvalidEmployeeException();
                }

                // got this far, set employee is valid to true
                employeeIsValid = true;

                if (employeeRole != null) {
                    switch (employeeRole.getRoleId()) {
                        case 1: {
                            Admin admin = getAdminByEmployeeId(model, employeeId);
                            updateEmployee(keyboard, model, admin, null, null, null);
                            break;
                        }

                        case 2: {
                            RegionalSalesManager regionalSalesManager = getRegionalSalesManagerByEmployeeId(model, employeeId);
                            updateEmployee(keyboard, model, null, regionalSalesManager, null, null);
                            break;
                        }

                        case 3: {
                            SalesManager salesManager = getSalesManagerByEmployeeId(model, employeeId);
                            updateEmployee(keyboard, model, null, null, salesManager, null);
                            break;
                        }

                        case 4: {
                            Salesman salesman = getSalesmanByEmployeeId(model, employeeId);
                            updateEmployee(keyboard, model, null, null, null, salesman);
                            break;
                        }

                        default: {
                            throw new InvalidRoleException();
                        }
                    }

                    System.out.println("\nEmployee updated");
                    // back to manage employees loop
                    manageEmployees(keyboard, model);
                } else {
                    throw new InvalidRoleException();
                }

            } catch (NumberFormatException | InvalidRoleException | InvalidEmployeeException ex) {
                System.out.println("\nInvalid employee, try again.");
            }
        } while (employeeRole == null || !employeeIsValid);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void editMotorbike(Scanner keyboard, Model model) {
        boolean motorbikeIsValid = false;
        int motorbikeId;

        do {
            try {
                System.out.println("\nEnter the motorbike ID : ");
                String line = keyboard.nextLine();
                motorbikeId = Integer.parseInt(line);

                // validate motorbike id
                if (validateMotorbikeId(model, motorbikeId) <= 0) {
                    throw new InvalidMotorbikeException();
                }

                // got this far, set motorbikeIsValid to true
                motorbikeIsValid = true;

                Motorbike motorbike = getMotorbikeById(model, motorbikeId);
                updateMotorbike(keyboard, model, motorbike);

                System.out.println("\nMotorbike updated");
                // back to manage motorbikes loop
                manageMotorbikes(keyboard, model);

            } catch (NumberFormatException | InvalidMotorbikeException ex) {
                System.out.println("\nInvalid motorbike, try again.");
            }
        } while (!motorbikeIsValid);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void assignRegionalSalesManager(Scanner keyboard, Model model, RegionalSalesManager regionalSalesManager) {
        List<SalesManager> salesManagers = new ArrayList<>();
        String confirm;

        System.out.println("\nAssign " + regionalSalesManager.getFirstName() + " to sales managers? [y] : ");
        confirm = keyboard.nextLine();
        confirm = confirm.toLowerCase();

        if (confirm.equals("") || confirm.equals("y")) {
            // list all sales managers who aren't supervised
            List<SalesManager> unsupervisedSalesManagers = getSalesManagers(model, true);
            if (unsupervisedSalesManagers.size() > 0) {
                // go through each of the sales managers
                for (SalesManager sm : unsupervisedSalesManagers) {
                    sm.printDetails();

                    System.out.println("\nAdd this sales manager? [y] : ");
                    confirm = keyboard.nextLine();
                    confirm = confirm.toLowerCase();

                    if (confirm.equals("") || confirm.equals("y")) {
                        // add to sales manager list
                        salesManagers.add(sm);
                    }
                }

                regionalSalesManager.setSalesManagers(salesManagers);
                saveSalesManagers(model, regionalSalesManager);
            } else {
                System.out.println("\nNo sales managers found");
            }
        }
    }


    /**
     * @param keyboard
     * @param model
     * @param salesManager
     */
    private static void assignSalesManager(Scanner keyboard, Model model, SalesManager salesManager) {
        List<Salesman> salesmen = new ArrayList<>();
        String confirm;

        System.out.println("\nAssign " + salesManager.getFirstName() + " to salesmen? [y] : ");
        confirm = keyboard.nextLine();
        confirm = confirm.toLowerCase();

        if (confirm.equals("") || confirm.equals("y")) {
            // list all salesmen who aren't supervised
            List<Salesman> unsupervisedSalesmen = getSalesmen(model, true);
            if (unsupervisedSalesmen.size() > 0) {
                // go through each of the salesmen
                for (Salesman s : unsupervisedSalesmen) {
                    s.printDetails();

                    System.out.println("\nAdd this salesman? [y] : ");
                    confirm = keyboard.nextLine();
                    confirm = confirm.toLowerCase();

                    if (confirm.equals("") || confirm.equals("y")) {
                        // add to salesmen list
                        salesmen.add(s);
                    }
                }

                salesManager.setSalesmen(salesmen);
                saveSalesmen(model, salesManager);
            } else {
                System.out.println("\nNo salesmen found");
            }
        }
    }

    /**
     * @param admin
     * @param regionalSalesManager
     * @param salesManager
     * @param salesman
     */
    private static void updateEmployee(Scanner keyboard, Model model, Admin admin, RegionalSalesManager regionalSalesManager,
                                       SalesManager salesManager, Salesman salesman) {
        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary, quarterlyBonus, commissionRate;

        try {
            System.out.println("Update first name : ");
            firstName = keyboard.nextLine();

            System.out.println("Update last name : ");
            lastName = keyboard.nextLine();

            System.out.println("Update email address : ");
            email = keyboard.nextLine();

            System.out.println("Update telephone : ");
            telephone = keyboard.nextLine();

            System.out.println("Update address : ");
            address = keyboard.nextLine();

            System.out.println("Update town : ");
            town = keyboard.nextLine();

            System.out.println("Update city : ");
            city = keyboard.nextLine();

            System.out.println("Update country : ");
            country = keyboard.nextLine();

            System.out.println("Update post code : ");
            postCode = keyboard.nextLine();

            System.out.println("Update salary : ");
            salary = keyboard.nextDouble();
            keyboard.nextLine();

            if (admin != null) {
                // updating admin
                admin.setFirstName(firstName);
                admin.setLastName(lastName);
                admin.setEmail(email);
                admin.setTelephone(telephone);
                admin.setAddress(address);
                admin.setTown(town);
                admin.setCity(city);
                admin.setPostCode(postCode);
                admin.setCountry(country);
                admin.setSalary(salary);

                updateAdmin(model, admin);
            } else if (regionalSalesManager != null) {
                // updating regional sales manager
                regionalSalesManager.setFirstName(firstName);
                regionalSalesManager.setLastName(lastName);
                regionalSalesManager.setEmail(email);
                regionalSalesManager.setTelephone(telephone);
                regionalSalesManager.setAddress(address);
                regionalSalesManager.setTown(town);
                regionalSalesManager.setCity(city);
                regionalSalesManager.setPostCode(postCode);
                regionalSalesManager.setCountry(country);
                regionalSalesManager.setSalary(salary);

                updateRegionalSalesManager(model, regionalSalesManager);
//                assignRegionalSalesManager(keyboard, model, regionalSalesManager);
            } else if (salesManager != null) {
                // updating sales manager
                System.out.println("Enter quarterly bonus : ");
                quarterlyBonus = keyboard.nextDouble();
                keyboard.nextLine();

                salesManager.setFirstName(firstName);
                salesManager.setLastName(lastName);
                salesManager.setEmail(email);
                salesManager.setTelephone(telephone);
                salesManager.setAddress(address);
                salesManager.setTown(town);
                salesManager.setCity(city);
                salesManager.setPostCode(postCode);
                salesManager.setCountry(country);
                salesManager.setSalary(salary);
                salesManager.setQuarterlyBonus(quarterlyBonus);

                updateSalesManager(model, salesManager);
//                assignSalesManager(keyboard, model, salesManager);
            } else if (salesman != null) {
                // updating salesman
                System.out.println("Enter commission rate : ");
                commissionRate = keyboard.nextDouble();
                keyboard.nextLine();

                salesman.setFirstName(firstName);
                salesman.setLastName(lastName);
                salesman.setEmail(email);
                salesman.setTelephone(telephone);
                salesman.setAddress(address);
                salesman.setTown(town);
                salesman.setCity(city);
                salesman.setPostCode(postCode);
                salesman.setCountry(country);
                salesman.setSalary(salary);
                salesman.setCommissionRate(commissionRate);

                updateSalesman(model, salesman);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    /**
     * @param keyboard
     * @param model
     * @param motorbike
     */
    private static void updateMotorbike(Scanner keyboard, Model model, Motorbike motorbike) {
        String make, _model, engine, colour;
        double price;
        int year, stock;

        try {
            System.out.println("Update make : ");
            make = keyboard.nextLine();

            System.out.println("Update model : ");
            _model = keyboard.nextLine();

            System.out.println("Update engine : ");
            engine = keyboard.nextLine();

            System.out.println("Update colour : ");
            colour = keyboard.nextLine();

            System.out.println("Update year : ");
            year = keyboard.nextInt();

            System.out.println("Update stock : ");
            stock = keyboard.nextInt();

            System.out.println("Update price : ");
            price = keyboard.nextDouble();
            keyboard.nextLine();

            motorbike.setMake(make);
            motorbike.setModel(_model);
            motorbike.setEngine(engine);
            motorbike.setColour(colour);
            motorbike.setYear(year);
            motorbike.setPrice(price);
            motorbike.setStock(stock);

            _updateMotorbike(model, motorbike);
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    /**
     * Create an instance of the EmployeeRole class from a given employee id
     *
     * @param model
     * @param employeeId
     * @return
     */
    private static EmployeeRole getEmployeeRole(Model model, int employeeId) {
        return model.getEmployeeRole(employeeId);
    }

    /**
     * Get a list of all employee roles
     *
     * @param model
     * @return roles
     */
    private static List<EmployeeRole> getEmployeeRoles(Model model) {
        return model.getEmployeeRoles();
    }

    /**
     * @param model
     * @return
     */
    private static List<MotorbikeFrame> getMotorbikeFrames(Model model) {
        return model.getMotorbikeFrames();
    }

    /**
     * Create an instance of Admin for given employee id
     *
     * @param model
     * @param employeeId
     * @return admin
     */
    private static Admin getAdminByEmployeeId(Model model, int employeeId) {
        return model.getAdminByEmployeeId(employeeId);
    }

    /**
     * @param model
     * @param motorbikeId
     * @return
     */
    private static Motorbike getMotorbikeById(Model model, int motorbikeId) {
        return model.getMotorbikeById(motorbikeId);
    }

    /**
     * @param model
     * @param sale
     */
    private static void addSale(Model model, Sale sale) {
        model.addSale(sale);
    }

    /**
     * @param model
     * @param employeeId
     * @return
     */
    private static RegionalSalesManager getRegionalSalesManagerByEmployeeId(Model model, int employeeId) {
        return model.getRegionalSalesManagerByEmployeeId(employeeId);
    }

    /**
     * @param model
     * @param employeeId
     * @return
     */
    private static SalesManager getSalesManagerByEmployeeId(Model model, int employeeId) {
        return model.getSalesManagerByEmployeeId(employeeId);
    }

    /**
     * @param model
     * @param employeeId
     * @return
     */
    private static Salesman getSalesmanByEmployeeId(Model model, int employeeId) {
        return model.getSalesmanByEmployeeId(employeeId);
    }

    /**
     * @param model
     * @param _admin
     */
    private static void addAdmin(Model model, Admin _admin) {
        model.addAdmin(_admin);
    }

    /**
     * @param model
     * @param motorbike
     */
    private static void storeMotorbike(Model model, Motorbike motorbike) {
        model.storeMotorbike(motorbike);
    }

    /**
     * @param model
     * @param _regionalSalesManager
     */
    private static void addRegionalSalesManager(Model model, RegionalSalesManager _regionalSalesManager) {
        model.addRegionalSalesManager(_regionalSalesManager);
    }

    /**
     * @param model
     * @param _salesManager
     */
    private static void addSalesManager(Model model, SalesManager _salesManager) {
        model.addSalesManager(_salesManager);
    }

    /**
     * @param model
     * @param _salesman
     */
    private static void addSalesman(Model model, Salesman _salesman) {
        model.addSalesman(_salesman);
    }

    /**
     * @param model
     * @param roleId
     * @return
     */
    private static EmployeeRole getEmployeeRoleById(Model model, int roleId) {
        return model.getEmployeeRoleById(roleId);
    }

    /**
     * @param model
     * @param frameId
     * @return
     */
    private static MotorbikeFrame getMotorbikeFrameById(Model model, int frameId) {
        return model.getMotorbikeFrameById(frameId);
    }

    /**
     * @param model
     * @return
     */
    private static List<Admin> getAdmins(Model model) {
        return model.getAdmins();
    }

    /**
     * @param model
     * @return
     */
    private static List<Motorbike> getMotorbikes(Model model) {
        return model.getMotorbikes();
    }

    /**
     * @param model
     * @return
     */
    private static List<Sale> getSales(Model model) {
        return model.getSales();
    }

    /**
     * @param model
     * @return
     */
    private static double viewTotalSumSales(Model model) {
        return model.getTotalSumSales();
    }

    /**
     * @param model
     * @return
     */
    private static int viewTotalNumMotorbikesSold(Model model) {
        return model.getTotalNumMotorbikesSold();
    }

    /**
     * @param model
     * @param status
     * @return
     */
    private static List<Sale> getSalesByStatus(Model model, String status) {
        return model.getSalesByStatus(status);
    }

    /**
     * @param model
     * @param salesmanId
     * @return
     */
    private static List<Sale> getSalesBySalesmanId(Model model, int salesmanId) {
        return model.getSalesBySalesmanId(salesmanId);
    }

    /**
     * @param model
     * @param frameId
     * @return
     */
    private static List<Motorbike> getMotorbikesByFrame(Model model, int frameId) {
        return model.getMotorbikesByFrame(frameId);
    }

    /**
     * @param model
     * @return
     */
    private static List<RegionalSalesManager> getRegionalSalesManagers(Model model) {
        return model.getRegionalSalesManagers();
    }

    /**
     * @param model
     * @return
     */
    private static List<SalesManager> getSalesManagers(Model model, boolean listUnsupervised) {
        return model.getSalesManagers(listUnsupervised);
    }

    /**
     * @param model
     * @return
     */
    private static List<Salesman> getSalesmen(Model model, boolean listUnsupervised) {
        return model.getSalesmen(listUnsupervised);
    }

    /**
     * @param model
     * @param admin
     */
    private static void updateAdmin(Model model, Admin admin) {
        model.updateAdmin(admin);
    }

    /**
     * @param model
     * @param motorbike
     */
    private static void _updateMotorbike(Model model, Motorbike motorbike) {
        model.updateMotorbike(motorbike);
    }

    /**
     * @param model
     * @param regionalSalesManager
     */
    private static void updateRegionalSalesManager(Model model, RegionalSalesManager regionalSalesManager) {
        model.updateRegionalSalesManager(regionalSalesManager);
    }

    /**
     * @param model
     * @param salesManager
     */
    private static void updateSalesManager(Model model, SalesManager salesManager) {
        model.updateSalesManager(salesManager);
    }

    /**
     * @param model
     * @param salesman
     */
    private static void updateSalesman(Model model, Salesman salesman) {
        model.updateSalesman(salesman);
    }

    /**
     * @param model
     * @param employeeId
     * @return
     */
    private static int validateEmployeeId(Model model, int employeeId) {
        return model.validateEmployeeId(employeeId);
    }

    /**
     * @param model
     * @param motorbikeId
     * @return
     */
    private static int validateMotorbikeId(Model model, int motorbikeId) {
        return model.validateMotorbikeId(motorbikeId);
    }


    /**
     * @param model
     * @param admin
     */
    private static void deleteAdmin(Model model, Admin admin) {
        model.deleteAdmin(admin);
    }

    /**
     * @param model
     * @param motorbike
     */
    private static void _deleteMotorbike(Model model, Motorbike motorbike) {
        model._deleteMotorbike(motorbike);
    }

    /**
     * @param model
     * @param regionalSalesManager
     */
    private static void deleteRegionalSalesManager(Model model, RegionalSalesManager regionalSalesManager) {
        model.deleteRegionalSalesManager(regionalSalesManager);
    }

    /**
     * @param model
     * @param salesManager
     */
    private static void deleteSalesManager(Model model, SalesManager salesManager) {
        model.deleteSalesManager(salesManager);
    }

    /**
     * @param model
     * @param salesman
     */
    private static void deleteSalesman(Model model, Salesman salesman) {
        model.deleteSalesman(salesman);
    }

    /**
     * @param regionalSalesManager
     */
    private static void saveSalesManagers(Model model, RegionalSalesManager regionalSalesManager) {
        model.saveSalesManagers(regionalSalesManager);
    }

    /**
     * @param model
     * @param salesManager
     */
    private static void saveSalesmen(Model model, SalesManager salesManager) {
        model.saveSalesmen(salesManager);
    }


    ///////////////////////////////// getters & setters ///////////////////////////////

    /**
     * @return
     */
    private static Admin getAdmin() {
        return admin;
    }

    /**
     * @param admin
     */
    private static void setAdmin(Admin admin) {
        JoesAUTOS.admin = admin;
    }

    /**
     * @return
     */
    private static RegionalSalesManager getRegionalSalesManager() {
        return regionalSalesManager;
    }

    /**
     * @param regionalSalesManager
     */
    private static void setRegionalSalesManager(RegionalSalesManager regionalSalesManager) {
        JoesAUTOS.regionalSalesManager = regionalSalesManager;
    }

    /**
     * @return
     */
    public static SalesManager getSalesManager() {
        return salesManager;
    }

    /**
     * @param salesManager
     */
    public static void setSalesManager(SalesManager salesManager) {
        JoesAUTOS.salesManager = salesManager;
    }

    /**
     * @return
     */
    public static Salesman getSalesman() {
        return salesman;
    }

    /**
     * @param salesman
     */
    public static void setSalesman(Salesman salesman) {
        JoesAUTOS.salesman = salesman;
    }
}
