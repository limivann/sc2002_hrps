# SC2002_HRPS

Hotel Reservation and Payment System (HRPS) for SC2002 Project

![HRPS preview](https://user-images.githubusercontent.com/71662324/184069519-65655f28-5b8d-4481-905a-38efbd3840ba.PNG)

# Setup Instructions

## Project Folder Structure

> Folder structure of our project

#### Top Level Directory Layout

```terminal
.
├── build                   # Compiled jar file
├── docs                    # Javadocs generated as html
├── src                     # Source files (all the codes)
├── LICENSE
└── README.md
```

#### Source files

```terminal
.
├── ...
├── src                    # Source files (all the codes)
│   ├── controller         # Managers classes
│   ├── database           # Database classes
│   ├── helper             # Helper classes
│   ├── model              # Model classes
│   ├── view               # View/Interface classes
│   └── HotelApp.java      # Main Driver file (HRPS App)
└── ...
```

## Scripts

> How to run our project

1. In your command line change directory into SC2002_HRPS

```terminal
D:\uni_tute\sc2002_java\Project\SC2002_HRPS>
```

2. Run the jar file using command line

```terminal
java -jar ./build/SC2002_HRPS.jar
```

3. Reset our database first before using our interface, steps are as follows

```terminal
╔═════════════════════════════════════════════════════════════════╗
║ Hotel App View                                                  ║
╚═════════════════════════════════════════════════════════════════╝
What would you like to do ?
(1) Manage Guest
(2) Manage Room
(3) Manage Reservation
(4) Manage Orders
(5) Manage Hotel Menu
(6) Manage Check In / Check Out
(7) Manage Promotion Details
(8) Manage Invoices
(9) Manage Database
(10) Exit HRPS
```

`Press "9" then <ENTER>`

```terminal
╔═════════════════════════════════════════════════════════════════╗
║ Hotel App View > Database View                                  ║
╚═════════════════════════════════════════════════════════════════╝
What would you like to do ?
(1) Initialize guests
(2) Initialize menu
(3) Reset database
(4) Exit Database View
```

`Press "3" then <ENTER>`

```terminal
╔═════════════════════════════════════════════════════════════════╗
║ Hotel App View > Database View > Reset database                 ║
╚═════════════════════════════════════════════════════════════════╝
Are you sure you want to reset the database? (yes/no)
```

`Type "yes" then <ENTER>`

```terminal
╔═════════════════════════════════════════════════════════════════╗
║ Hotel App View > Database View > Reset database                 ║
╚═════════════════════════════════════════════════════════════════╝
Are you sure you want to reset the database? (yes/no)
yes
Database cleared
Press Enter key to continue...
```

`Database is cleared successfully if the above message is shown`

# Java docs

Create javadocs - make sure you are at SC2002_HRPS directory

```terminal
javadoc -d ./docs/ ./src/*java ./src/controller/*java ./src/database/*java ./src/helper/*java ./src/model/*java ./src/model/enums/*java ./src/view/*java -encoding ISO-8859-1
```

Launch the index.html under ./javadoc/index.html

# Contributors

- @limivann
- @MAXTAN3110
- @kcz358
- @HillSeahWQ
- @lordAaron0121
