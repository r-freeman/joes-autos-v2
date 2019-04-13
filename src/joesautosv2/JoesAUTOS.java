/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

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
        Role role;
        int employeeId;

        do {
            try {
                System.out.println("\nEnter Employee ID : ");
                String line = keyboard.nextLine();
                employeeId = Integer.parseInt(line);

                role = getEmployeeRole(model, employeeId);

                System.out.println("Enter password : ********");

                switch (role.getRoleId()) {
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
     * The admin menu, admin can create, read, update and delete employees
     *
     * @param keyboard
     * @param model
     * @param
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
            System.out.println("1. Manage my sales managers");
            System.out.println("2. Log out");
            System.out.println("\nWelcome back " + regionalSalesManager.getFirstName() + ", enter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        manageEmployees(keyboard, model);
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
    private static void salesManagerMenu(Scanner keyboard, Model model) {
        int opt = 0;

        salesManagerMenu:
        do {
            System.out.println("\n******** SALES MANAGER MENU ********");
            System.out.println("1. Manage my salesmen");
            System.out.println("2. Log out");
            System.out.println("\nWelcome back " + salesManager.getFirstName() + ", enter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        manageEmployees(keyboard, model);
                        break salesManagerMenu;
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

    private static void salesmanMenu(Scanner keyboard, Model model) {
        int opt = 0;

        salesmanMenu:
        do {
            System.out.println("\n******** SALESMAN MENU ********");
            System.out.println("1. View all motorbikes");
            System.out.println("2. Filter motorbikes by frame");
            System.out.println("3. Create new sale");
            System.out.println("4. View my sales");
            System.out.println("5. Log out");
            System.out.println("\nWelcome back " + salesman.getFirstName() + ", enter option : ");

            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                switch (opt) {
                    case 1: {
                        manageEmployees(keyboard, model);
                        break salesmanMenu;
                    }

                    case 2: {
                        break;
                    }

                    case 3: {
                        break salesmanMenu;
                    }

                    case 4: {
                        break salesmanMenu;
                    }

                    case 5: {
                        break salesmanMenu;
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
        System.out.println("\nGoodbye!");
        System.exit(0);
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
            System.out.println("5. Admin menu");
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
        Role role;
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

                role = getEmployeeRole(model, employeeId);

                switch (role.getRoleId()) {
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
    private static void viewEmployeesByRole(Scanner keyboard, Model model) {
        Role role = null;

        do {
            try {
                System.out.println("\nEmployee roles");
                listEmployeeRoles(model);
                System.out.println("\nEnter role number : ");
                role = validateEmployeeRole(keyboard, model);

                if (role != null) {
                    switch (role.getRoleId()) {
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
                            List<Salesman> salesmen = getSalesmen(model);
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
        } while (role == null);
    }

    /**
     * Small function for listing all employee roles
     *
     * @param model
     */
    private static void listEmployeeRoles(Model model) {
        List<Role> roles;
        roles = getEmployeeRoles(model);
        for (Role r : roles) r.printDetails();
    }

    /**
     * @param keyboard
     * @param model
     * @return
     */
    private static Role validateEmployeeRole(Scanner keyboard, Model model) {
        String line = keyboard.nextLine();
        int roleId = Integer.parseInt(line);
        Role role = null;

        if (Integer.class.isInstance(roleId)) {
            role = getRoleById(model, roleId);
        }

        return role;
    }

    /**
     * @param keyboard
     * @param model
     */
    public static void addEmployee(Scanner keyboard, Model model) {
        Role role = null;

        do {
            try {
                System.out.println("\nWhat is the employee's role?");
                listEmployeeRoles(model);
                System.out.println("\nEnter role number : ");

                // make sure the role id is valid
                role = validateEmployeeRole(keyboard, model);

                if (role != null) {
                    readEmployee(keyboard, model, role);
                    // back to manage employees loop
                    manageEmployees(keyboard, model);
                } else {
                    throw new InvalidRoleException();
                }

            } catch (NumberFormatException | InvalidRoleException ex) {
                System.out.println("\nInvalid employee role, try again.");
            }
        } while (role == null);
    }

    /**
     * @param keyboard
     * @param model
     * @param role
     */
    private static void readEmployee(Scanner keyboard, Model model, Role role) {
        String firstName, lastName, email, telephone,
                address, town, city, country, postCode, confirm;
        double salary, quarterlyBonus, commissionRate;

        try {
            System.out.println("Enter first name : ");
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

            switch (role.getRoleId()) {
                case 1: {
                    Admin _admin = new Admin(firstName, lastName, email, telephone, address,
                            town, city, country, postCode, salary, role.getRoleId()
                    );
                    addAdmin(model, _admin);
                    break;
                }

                case 2: {
                    RegionalSalesManager _regionalSalesManager = new RegionalSalesManager(
                            firstName, lastName, email, telephone, address, town, city,
                            country, postCode, salary, role.getRoleId()
                    );
                    addRegionalSalesManager(model, _regionalSalesManager);
                    break;
                }

                case 3: {
                    System.out.println("Enter quarterly bonus : ");
                    quarterlyBonus = keyboard.nextDouble();
                    keyboard.nextLine();

                    SalesManager _salesManager = new SalesManager(firstName, lastName, email, telephone, address,
                            town, city, country, postCode, salary, role.getRoleId(), quarterlyBonus);
                    addSalesManager(model, _salesManager);
                    break;
                }

                case 4: {
                    System.out.println("Enter commission rate : ");
                    commissionRate = keyboard.nextDouble();
                    keyboard.nextLine();

                    Salesman _salesman = new Salesman(firstName, lastName, email, telephone, address,
                            town, city, country, postCode, salary, role.getRoleId(), commissionRate);

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
     */
    private static void editEmployee(Scanner keyboard, Model model) {
        String confirm;
        boolean employeeIsValid = false;
        int employeeId;
        Role role = null;

        do {
            try {
                System.out.println("\nWhat is the employee's role?");
                listEmployeeRoles(model);
                System.out.println("\nEnter role number : ");
                // make sure the role id is valid
                role = validateEmployeeRole(keyboard, model);

                System.out.println("\nEnter the employee's ID : ");
                String line = keyboard.nextLine();
                employeeId = Integer.parseInt(line);

                // validate employee id
                if (validateEmployeeId(model, employeeId) <= 0) {
                    throw new InvalidEmployeeException();
                }

                // got this far, set employee is valid to true
                employeeIsValid = true;

                if (role != null) {
                    switch (role.getRoleId()) {
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
        } while (role == null || !employeeIsValid);
    }

    /**
     * @param keyboard
     * @param model
     */
    private static void assignRegionalSalesManager(Scanner keyboard, Model model, RegionalSalesManager regionalSalesManager) {
        String confirm;

        System.out.println("\nAssign " + regionalSalesManager.getFirstName() + " to a sales manager? [y] : ");
        confirm = keyboard.nextLine();
        confirm = confirm.toLowerCase();

        if (confirm.equals("") || confirm.equals("y")) {
            // list all sales managers who aren't supervised
            List<SalesManager> unsupervisedSalesManagers = getSalesManagers(model, true);
            for(SalesManager sm : unsupervisedSalesManagers) {
                sm.printDetails();
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
                assignRegionalSalesManager(keyboard, model, regionalSalesManager);
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
     * Create an instance of the Role class from a given employee id
     *
     * @param model
     * @param employeeId
     * @return
     */
    private static Role getEmployeeRole(Model model, int employeeId) {
        return model.getEmployeeRole(employeeId);
    }

    /**
     * Get a list of all employee roles
     *
     * @param model
     * @return roles
     */
    private static List<Role> getEmployeeRoles(Model model) {
        return model.getEmployeeRoles();
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
    private static Role getRoleById(Model model, int roleId) {
        return model.getRoleById(roleId);
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
    private static List<Salesman> getSalesmen(Model model) {
        return model.getSalesmen();
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
     * @param admin
     */
    private static void deleteAdmin(Model model, Admin admin) {
        model.deleteAdmin(admin);
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
