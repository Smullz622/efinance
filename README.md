# efinance
ist 412 group 4 efinance app

refactor implementations:
1) Add validation logic and id variable to main menu methods (Sarah)
    In CustomerController, the validateLogin method was changed to pass the validated customer's id to the customerMenu method so that the current customer's id
    can be retained. This also required a new method in CustomerService/CustomerServiceImpl that takes in login credentials (email/password) and returns that
    customers id. The customerMenu template was updated accordingly as well.

2) Organize templates into packages (Sarah)
    All templates were organized into subdirectories. They were generally categorized by related model class. So far, we have customer, employee, loan_application, 
    and error_pages.
 
3) Remove unnecessary templates (Sarah)
    Some of our error templates were redundant. The customer_login_error and employee_login_error were condensed into account_not_found_error which can also
    be used for any errors in password retrieval.
 
4) Remove LoginController, create EmployeeController and CustomerController (Kirstin)

5) Add data validations to user input (Ameli)

6) Remove unused imports (Kirstin)

7) Add ‘Cancel’ button to Loan Application interface. (Kirstin)

8) Add Log Out feature (Sungjin)
    There was no log out function on the application which can cause some security issues. Added log out feature in EmployeeController class.
    Created Log out button on customer_menu.html and employee_menu.html files.

9) Add Find Password feature (Sungjin/Sarah)
    Methods were added to the EmployeeController and CustomerController to allow users to recover their forgotten password. This also required the creation of 
    new templates used to allow users to input their email and be shown their password.

10) Remove unnecessary getters and setters (Sungjin)
    Deleted unused getters and setters which have the potential to increase the complexity of the application.

12) Reformat to add line wrapping (Ameli)

13) Remove name section from the loan application and all related variables in application HMTL code (Ameli)
