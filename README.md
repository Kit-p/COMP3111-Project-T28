# Team T-28
> <https://github.com/Kit-p/COMP3111-Project-T28.git>

## GitHub Page
> [](https://kit-p.github.io/COMP3111-Project-T28)

---

# Members
## 1. PANG, Kit

> email: kpangaa | github: Kit-p | dev branch: kpangaa_dev

> Task 3 & Task 6

## 2. TAM, Tsz Chung

> email: tctam | github: TAMTszChung | dev branch: tctam_dev

> Task 2 & Task 5

## 3. WU, Tsz Yeung

> email: tywuab | github: tywuab | dev branch: tywuab_dev

> Task 1 & Task 4

---

# Tasks
All screenshots below can be found [here](supplementary/resources/Screenshots)!

## Task 1 (Reporting 1)
> **Description:**  
> {{placeholder}}

> **Demonstrations:**
> 
> *Valid input with output*
> ![task1_valid](supplementary/resources/Screenshots/Task1/Task1.PNG)
> 
> *Invalid input with alert (N is out of expected range)*
> ![task1_invalid](supplementary/resources/Screenshots/Task1/Task1Invalid.PNG)

## Task 2 (Reporting 2)
> **Description:**  
> {{placeholder}}

> **Demonstrations:**
> 
> *Valid input with output (case 1)*
> ![task2_valid_case1](supplementary/resources/Screenshots/Task2/Task2ValidCase1.png)
> 
> *Valid input with output (case 2)*
> ![task2_valid_case2](supplementary/resources/Screenshots/Task2/Task2ValidCase2.png)
> 
> *Invalid input with alert (name is blank)*
> ![task2_invalid_case1](supplementary/resources/Screenshots/Task2/Task2InvalidCaseBlankName.png)
> 
> *Invalid input with alert (period is out of expected range)*
> ![task2_invalid_case2](supplementary/resources/Screenshots/Task2/Task2InvalidCaseInvalidPeriod.png)

## Task 3 (Reporting 3)
> **Description:**  
> This is a service that generates a brief report in response to the queries 
> on identifying the names that have maintained a high level of popularity 
> within Top N over a given period.

> **Demonstrations:**
> 
> *Valid input with output (case 1)*
> ![task3_valid_case1](supplementary/resources/Screenshots/Task3/Task3_valid1.png)
> 
> *Valid input with output (case 2)*
> ![task3_valid_case2](supplementary/resources/Screenshots/Task3/Task3_valid2.png)
> 
> *Invalid input with alert (N is out of expected range)*
> ![task3_invalid_case1](supplementary/resources/Screenshots/Task3/Task3_invalidN1.png)
> 
> *Invalid input with alert (N is blank)*
> ![task3_invalid_case2](supplementary/resources/Screenshots/Task3/Task3_invalidN2.png)
> 
> *Invalid input with alert (year is out of expected range)*
> ![task3_invalid_case3](supplementary/resources/Screenshots/Task3/Task3_invalidYear1.png)
>
> *Invalid input with alert (year is out of expected range)*
> ![task3_invalid_case4](supplementary/resources/Screenshots/Task3/Task3_invalidYear2.png)
> 
> *Invalid input with alert (period is invalid)*
> ![task3_invalid_case4](supplementary/resources/Screenshots/Task3/Task3_invalidYear3.png)

## Task 4 (Application 1)
> **Description:**  
> {{placeholder}}

> **Algorithm:**  
> {{placeholder}}

> **Justification:**  
> {{placeholder}}

> **Demonstrations:**
> 
> *Valid input with output*
> ![task4_valid](supplementary/resources/Screenshots/Task4/Task4.PNG)
> 
> *Invalid input with alert (year of birth is not a valid year)*
> ![task4_invalid_case1](supplementary/resources/Screenshots/Task4/Task4Invalid.PNG)
> 
> *Invalid input with alert (vintage year is out of expected range)*
> ![task4_invalid_case2](supplementary/resources/Screenshots/Task4/Task4Invalid2.PNG)

## Task 5 (Application 2)
> **Description:**  
> {{placeholder}}

> **Algorithm:**  
> {{placeholder}}

> **Justification:**  
> {{placeholder}}

> **Demonstrations:**
>
> *Valid input with output (case 1)*
> ![task5_valid_case1](supplementary/resources/Screenshots/Task5/Task5ValidCase1.png)
> 
> *Valid input with output (case 2)*
> ![task5_valid_case2](supplementary/resources/Screenshots/Task5/Task5ValidCase2.png)
> 
> *Invalid input with alert (name is blank)*
> ![task5_invalid_case1](supplementary/resources/Screenshots/Task5/Task5InvalidCaseBlankName.png)
> 
> *Invalid input with alert (year of birth is out of expected range)*
> ![task5_invalid_case2](supplementary/resources/Screenshots/Task5/Task5InvalidYOB.png)
> 
> *Invalid input with alert (age preference with given year is out of expected range)*
> ![task5_invalid_case3](supplementary/resources/Screenshots/Task5/Task5InvalidYOB1880younger.png)
> 
> *Invalid input with alert (age preference with given year is out of expected range)*
> ![task5_invalid_case4](supplementary/resources/Screenshots/Task5/Task5InvalidYOB2019older.png)

# Task 6 (Application 3)
> **Description:**  
> This is a service using empirical data to help you make informed decisions 
> on predicting the compatibility of you and your partner (i.e. Soulmate).
> 
> The predicted compatibility will be corrected to 2 decimal places.

> **Algorithm:**  
> The algorithm computes the difference between 
> the ranks of the names of the pair in their own year, 
> and the maximum possible difference between the ranks of the names of the pair in their own year.
> 
> The ratio implies how close their ranks are and is then converted to percentage (corrected to 2 d.p.).
> 
> The percentage is the predicted compatibility of the pair.
> 
> Exception case is when any of the names of the pair is not ranked on the corresponding year, 
> the algorithm will substitute it with the lowest possible rank of the corresponding gender of the corresponding year.

> **Justification:**  
> The original NK-T6 Algorithm of Universal Compatibility computes a variation of the percentage difference, 
> which will result in values that are smaller than 0% or greater than 100%.
> 
> The modified algorithm uses the rank difference as the numerator and the maximum difference as the denominator.
> 
> Since the difference is always greater than or equal to zero, 
> and is always smaller than or equal to the maximum difference, 
> the result will always be between 0% and 100%, which is consistent with the expected output format.
> 
> The algorithm is used because we believe it is difficult for the two names of the pair to have the same rank. 
> The closer the ranks are, the lower the possibility is. Such rarity resembles the concept of serendipity, which we use as the criteria for predicting compatibility.
> 
> The reason why the algorithm substitutes the rank of names that is not ranked 
> with the lowest rank of the corresponding gender of the corresponding year, 
> is to ensure the user will always see a reasonable output (0% - 100%), 
> no matter what names the input is, so they will not be disappointed with an error. 
> Choosing the lowest possible rank is because that is the best estimate of the rank, 
> given that names that are not ranked would be rare and low on occurrence if not none.

> **Demonstrations:**
>
> *Valid input with output (case 1)*
> ![task6_valid_case1](supplementary/resources/Screenshots/Task6/Task6_valid1.png)
> 
> *Valid input with output (case 2)*
> ![task6_valid_case2](supplementary/resources/Screenshots/Task6/Task6_valid2.png)
> 
> *Invalid input with alert (name is blank)*
> ![task6_invalid_case1](supplementary/resources/Screenshots/Task6/Task6_invalidName.png)
> 
> *Invalid input with alert (age preference with given year is out of expected range)*
> ![task6_invalid_case2](supplementary/resources/Screenshots/Task6/Task6_invalidPreference1.png)
> 
> *Invalid input with alert (age preference with given year is out of expected range)*
> ![task6_invalid_case3](supplementary/resources/Screenshots/Task6/Task6_invalidPreference2.png)
> 
> *Invalid input with alert (year of birth is out of expected range)*
> ![task6_invalid_case4](supplementary/resources/Screenshots/Task6/Task6_invalidYear1.png)
> 
> *Invalid input with alert (year of birth is blank)*
> ![task6_invalid_case5](supplementary/resources/Screenshots/Task6/Task6_invalidYear2.png)

---

# Reports
## Test Report
The test report can be found [here](https://kit-p.github.io/COMP3111-Project-T28/supplementary/reports/tests/test/index.html)!
![test_report](supplementary/resources/Screenshots/Reports/testReport.png)

## Coverage Report
The coverage report can be found [here](https://kit-p.github.io/COMP3111-Project-T28/supplementary/reports/jacocoHTML/index.html)!
![coverage_report](supplementary/resources/Screenshots/Reports/coverageReport.png)

---

# Documentations
The documentations (generated by JavaDoc) can be found [here](https://kit-p.github.io/COMP3111-Project-T28/docs/index.html)!

---

# Supplementary Notes
The supplementary notes can be found [here](supplementary/supplementaryNotes.pdf)!