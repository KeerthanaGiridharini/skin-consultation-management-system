//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867636
//Date: 08/01/2023

package com.keerthana;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.keerthana.WestminsterSkinConsultationManager.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GUI {

    private final JFrame frame;
    private  JFrame frame1;
    private JFrame frame2;
    private JFrame viewBookingsFrame;
    JTextField costField;
    private WestminsterSkinConsultationManager westminsterSkinConsultationManager = new WestminsterSkinConsultationManager();

    public GUI(ArrayList<Doctor>doctors) {
        WestminsterSkinConsultationManager.doctors = doctors;

        //Inserting an image for the main frame (Menu frame )
        ImageIcon docIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/doc1.png")));
        JLabel mylabel = new JLabel(docIcon);
        mylabel.setSize(255,442);
        mylabel.setBounds(145,100,255,442);
        frame = new JFrame("Westminster Skin Consultation Center");
        frame.add(mylabel); //adding image to the frame


        JLabel l1;
        l1 = new JLabel("Welcome to Westminster skin consultation center..");
        l1.setBounds(80, 20, 700, 30);
        l1.setSize(700, 50);
        l1.setFont(new Font("Serif Bold", Font.BOLD, 27));
        frame.add(l1); //adding label to the frame


        //setting the main frame size & visibility
        frame.setSize(850, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //  Button of viewing the list of doctors
        JButton b1 = new JButton("View Doctors");
        b1.setBounds(455, 200, 300, 35);
        b1.setFont(new Font("Arial", Font.BOLD, 17));
        b1.setBackground(Color.white);
        b1.setBorder(new LineBorder(Color.lightGray));
        frame.add(b1);
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); //closing the main menu
                frame1 = new JFrame("Doctor table"); //displaying another frame

                //Back Button
                JButton backBtn = new JButton("Main Menu");
                backBtn.setBounds(225, 270, 200, 30);
                backBtn.setFont(new Font("Arial", Font.BOLD, 15));
                backBtn.setBorder(new LineBorder(Color.black));
                backBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                        frame.setVisible(true);
                    }
                });

                //sorting Button --> This button will sort the doctors first names in to the alphabetical order
                JButton sortBtn = new JButton("Sort in Alphabetical ");
                sortBtn.setBounds(465, 270, 200, 30);
                sortBtn.setFont(new Font("Arial", Font.BOLD, 15));
                sortBtn.setBorder(new LineBorder(Color.black));
                sortBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Sort the list of doctors alphabetically according to the doctor's name
                        doctors.sort(new Comparator<Doctor>() {
                            @Override
                            public int compare(Doctor doc1, Doctor doc2) {
                                return doc1.getName().compareTo(doc2.getName());
                            }
                        });
                        table(frame1);
                        frame1.setVisible(true);
                        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame1.setSize(850, 550);
                        frame1.setLocationRelativeTo(null);
                    }
                });
                frame1.add(backBtn);
                frame1.add(sortBtn);
                table(frame1);
                frame1.setVisible(true);
                frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frame1.setSize(850, 380);
                frame1.setLocationRelativeTo(null);

            }
        });

        /*  Button of booking a consultation
        This will display getting patient details with the availability of doctors & that will
        help to book a consultation to the user */
        JButton b2 = new JButton("Book a consultation");
        b2.setBounds(455, 250, 300, 35);
        b2.setFont(new Font("Serif Bold", Font.BOLD, 17));
        b2.setBackground(Color.white);
        b2.setBorder(new LineBorder(Color.lightGray));
        frame.add(b2);
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame2=new JFrame("Consultation");
                consultation(frame2);
                frame2.setVisible(true);
            }

        });


        //  Button of viewing the list of doctors
        JButton b3 = new JButton("View Pre booked Consultations");
        b3.setBounds(455, 300, 300, 35);
        b3.setFont(new Font("Serif Bold", Font.BOLD, 17));
        b3.setBackground(Color.white);
        b3.setBorder(new LineBorder(Color.lightGray));
        frame.add(b3);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                viewBookingsFrame = new JFrame("Consultation Table");
                viewBookingsFrame.setSize(1100,375);
                String[] colums = {"Patient Id","Date","Time","Patient's Name","Patient's Surname","Patient's DOB","Patient tel No","No of Hours","Doctor's Name","Specialization","Cost","Note"};
                DefaultTableModel tableModel = new DefaultTableModel(colums,11);
                JTable patientTable = new JTable(tableModel);
                JScrollPane tablePane = new JScrollPane(patientTable);
                tableModel.setRowCount(0);
                Object[] rowData = new Object[12];

                for (Consultation consultation:consultationArrayList) {

                    rowData[0] = consultation.getPatient().getPatientId();
                    // Simple Date Format for format the output to DD/YY/MMM format.
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    rowData[1] = sdf.format(consultation.getDateTime());
                    // Simple Date Format for format the output to HH:mm:ss format.
                    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
                    rowData[2] = time.format(consultation.getTime());
                    rowData[3] = consultation.getPatient().getName();
                    rowData[4] = consultation.getPatient().getSurname();
                    rowData[5] = consultation.getPatient().getDateOfBirth();
                    rowData[6] = consultation.getPatient().getMobileNumber();
                    rowData[7] = consultation.getHours();
                    rowData[8] = consultation.getDoctor().getName();
                    rowData[9] = consultation.getDoctor().getSpecialisation();
                    rowData[10] = consultation.getCost();
                    rowData[11] = consultation.getNotes();
                    tableModel.addRow(rowData);
                }

                patientTable.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        viewBookingsFrame.dispose();
                        JFrame bookingDetails = new JFrame("Booking Details");
                        bookingDetails.setSize(400,800);

                        JLabel lblHeading = new JLabel("           Westminster Skin Consultation Bookings");
                        lblHeading.setFont(new Font("Arial",Font.BOLD,15));
                        bookingDetails.getContentPane().add(lblHeading,BorderLayout.PAGE_START);
                        JLabel patientIdLabel = new JLabel("Patient ID ");
                        JLabel conDate = new JLabel("Date ");
                        JLabel conTime = new JLabel("Time ");
                        JLabel fNameLabel = new JLabel("Patient first Name ");
                        JLabel sNameLabel = new JLabel("Patient Second Name ");
                        JLabel phNumLabel = new JLabel("Patient Contact No ");
                        JLabel dobLabel = new JLabel("Patient Date of Birth ");
                        JLabel hoursLabel = new JLabel("No of Hours ");
                        JLabel docName = new JLabel("Doctor's Name ");
                        JLabel docSpecialization = new JLabel("Specialization ");
                        JLabel notes = new JLabel("Notes ");
                        JLabel cost = new JLabel("Cost ");
                        JLabel image = new JLabel("Image ");

                        JLabel patientIdLabel1 = new JLabel();
                        JLabel conDate1 = new JLabel();
                        JLabel conTime1 = new JLabel();
                        JLabel fNameLabel1 = new JLabel();
                        JLabel sNameLabel1 = new JLabel();
                        JLabel phNumLabel1= new JLabel();
                        JLabel dobLabel1 = new JLabel();
                        JLabel hoursLabel1 = new JLabel();
                        JLabel docName1 = new JLabel();
                        JLabel docSpecialization1 = new JLabel();
                        JLabel notes1 = new JLabel();
                        JLabel cost1 = new JLabel();
                        ImageIcon skinIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Skin/Skin3.jpg")));
                        JLabel mylabel = new JLabel(skinIcon);

                        patientIdLabel.setBounds(10,30,100,20);
                        conDate.setBounds(10,60,150,20);
                        conTime.setBounds(10,90,150,20);
                        fNameLabel.setBounds(10,120,150,20);
                        sNameLabel.setBounds(10,150,150,20);
                        phNumLabel.setBounds(10,180,150,20);
                        dobLabel.setBounds(10,210,150,20);
                        hoursLabel.setBounds(10,240,150,20);
                        docName.setBounds(10,500,150,20);
                        docSpecialization.setBounds(10,530,150,20);
                        notes.setBounds(10,560,150,20);
                        cost.setBounds(10,590,150,20);
                        image.setBounds(10,270,150,20);


                        patientIdLabel1.setBounds(180,30,100,20);
                        conDate1.setBounds(180,60,150,20);
                        conTime1.setBounds(180,90,150,20);
                        fNameLabel1.setBounds(180,120,150,20);
                        sNameLabel1.setBounds(180,150,150,20);
                        phNumLabel1.setBounds(180,180,150,20);
                        dobLabel1.setBounds(180,210,150,20);
                        hoursLabel1.setBounds(180,240,150,20);
                        docName1.setBounds(180,270,150,20);
                        docSpecialization1.setBounds(180,300,150,20);
                        notes1.setBounds(180,330,150,20);
                        cost1.setBounds(180,360,150,20);
                        mylabel.setBounds(250,700,300,300);

                        String id = (String) patientTable.getValueAt(patientTable.getSelectedRow(),4);
                        for (Consultation consultation: consultationArrayList) {
                            if(id.trim().equalsIgnoreCase(consultation.getPatient().getPatientId())){
                                patientIdLabel1.setText(consultation.getPatient().getPatientId());
                                conDate1.setText(String.valueOf(consultation.getDateTime()));
                                conTime1.setText(String.valueOf(consultation.getTime()));
                                fNameLabel1.setText(consultation.getPatient().getName());
                                sNameLabel1.setText(consultation.getPatient().getSurname());
                                phNumLabel1.setText(consultation.getPatient().getMobileNumber());
                                dobLabel1.setText(consultation.getDoctor().getName());
                                docSpecialization1.setText(consultation.getDoctor().getSpecialisation());
                                notes1.setText(consultation.getNotes());
                                cost1.setText(String.valueOf(consultation.getCost()));


                            }
                        }

                        bookingDetails.add(patientIdLabel);
                        bookingDetails.add(fNameLabel);
                        bookingDetails.add(sNameLabel);
                        bookingDetails.add(phNumLabel);
                        bookingDetails.add(dobLabel);
                        bookingDetails.add(hoursLabel);
                        bookingDetails.add(docName);
                        bookingDetails.add(conDate);
                        bookingDetails.add(conTime);
                        bookingDetails.add(docSpecialization);
                        bookingDetails.add(notes);
                        bookingDetails.add(cost);
                        bookingDetails.add(image);

                        bookingDetails.add(patientIdLabel1);
                        bookingDetails.add(fNameLabel1);
                        bookingDetails.add(sNameLabel1);
                        bookingDetails.add(phNumLabel1);
                        bookingDetails.add(dobLabel1);
                        bookingDetails.add(hoursLabel1);
                        bookingDetails.add(docName1);
                        bookingDetails.add(conDate1);
                        bookingDetails.add(conTime1);
                        bookingDetails.add(docSpecialization1);
                        bookingDetails.add(notes1);
                        bookingDetails.add(cost1);
                        bookingDetails.add(mylabel);

                        bookingDetails.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        bookingDetails.setLocationRelativeTo(null);
                        bookingDetails .setVisible(true);

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });

                JButton backButton = new JButton("Main Menu");
                viewBookingsFrame.add(backButton);
                backButton.setBounds(410,270,110,25);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewBookingsFrame.dispose();
                        frame.setVisible(true);

                    }
                });

                JButton quit = new JButton("Quit");
                viewBookingsFrame.add(quit);
                quit.setBounds(580,270,110,25);
                quit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewBookingsFrame.dispose();
                    }
                });

                JLabel lblHeading = new JLabel("                                              " +
                        "                                                        Westminster Skin Consultation Bookings");
                lblHeading.setFont(new Font("Arial",Font.BOLD,14));
                viewBookingsFrame.getContentPane().add(lblHeading,BorderLayout.PAGE_START);
                viewBookingsFrame.add(tablePane);
                viewBookingsFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                viewBookingsFrame.setLocationRelativeTo(null);
                viewBookingsFrame.setVisible(true);

            }

        });

        /*Quit Button
        closes the main frame when the Quit button is clicked*/
        JButton quitBtn = new JButton("Quit");
        quitBtn.setBounds(455, 350, 300, 35);
        quitBtn.setFont(new Font("Serif Bold", Font.BOLD, 17));
        quitBtn.setMargin(new Insets(5, 30, 5, 30));
        quitBtn.setBackground(Color.white);
        quitBtn.setBorder(new LineBorder(Color.lightGray));
        frame.add(quitBtn);

        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                westminsterSkinConsultationManager.saveInformation();
                frame.dispose();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                westminsterSkinConsultationManager.saveInformation();
                super.windowClosing(e);
            }
        });

    }

    public void table (JFrame thisFrame){

        JTable docTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        String [] coloumNames = {"No","Name","Surname","Date Of Birth","Mobile Number","Medical Licence No","Specialization"};
        tableModel.setColumnIdentifiers(coloumNames);
        docTable.setModel(tableModel);

        JLabel lblHeading = new JLabel("                                              " +
                "                        List of Doctors");
        lblHeading.setFont(new Font("Arial",Font.BOLD,18));
        frame1.getContentPane().add(lblHeading,BorderLayout.PAGE_START);


        Object[] row = new Object[7];
        int i = 1;
        for(Doctor doctor: doctors){
            row[0] = i++;
            row[1] = doctor.getName();
            row[2] = doctor.getSurname();
            // Simple Date Format for format the output to DD/YY/MMM format.
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            row[3] = sdf.format(doctor.getDateOfBirth());
            row[4] = doctor.getMobileNumber();
            row[5] = doctor.getMedicalLicenceNumber();
            row[6] = doctor.getSpecialisation();
            tableModel.addRow(row);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        docTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        docTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        docTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        docTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        docTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        docTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        docTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        docTable.setFont(new Font("Serif", Font.PLAIN, 14));
        docTable.setRowHeight(docTable.getRowHeight() + 12);
        JScrollPane tablePane = new JScrollPane(docTable);
        thisFrame.add(tablePane);
        setJTableColumnsWidth(docTable, 480, 7, 25, 25, 25,30,30,30);
    }

    public void consultation(JFrame thisFrame) {

        thisFrame.setSize(900, 600);
        JPanel heading = new JPanel();
        heading.add(new JLabel("Add Consultation"));
        thisFrame.add("North",heading );

        String [] coloumNames = {"No","Name","Surname","Date Of Birth","Mobile Number","Medical Licence Number","Specialization"};
        DefaultTableModel tableModel = new DefaultTableModel(coloumNames,5);
        JTable doctorTable = new JTable(tableModel);
        JScrollPane tablePane = new JScrollPane(doctorTable);
        tableModel.setRowCount(0);
        int i=1;
        Object[] row = new Object[7];
        for(Doctor doctor: doctors){
            row[0] = i++;
            row[1] = doctor.getName();
            row[2] = doctor.getSurname();
            // Simple Date Format for format the output to DD/YY/MMM format.
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            row[3] = sdf.format(doctor.getDateOfBirth());
            row[4] = doctor.getMobileNumber();
            row[5] = doctor.getMedicalLicenceNumber();
            row[6] = doctor.getSpecialisation();
            tableModel.addRow(row);
        }



        thisFrame.add("Center",tablePane);


        JPanel panel = new JPanel();


        JPanel leftPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        JLabel docHeading = new JLabel("");
        leftPanel.add(docHeading);

        leftPanel.add(new JLabel(""));
        leftPanel.add(new JLabel("Doctor first Name : "));
        JTextField doctorFNameField = new JTextField(10);
        leftPanel.add(doctorFNameField);
        leftPanel.add(new JLabel("Doctor last Name  : "));
        JTextField doctorLNameField = new JTextField(10);
        leftPanel.add(doctorLNameField);
        leftPanel.add(new JLabel("Doctor Date of Birth  : "));
        JTextField doctorDobField = new JTextField(10);
        leftPanel.add(doctorDobField);
        leftPanel.add(new JLabel("Doctor Mobile Number  : "));
        JTextField doctorMobField = new JTextField(10);
        leftPanel.add(doctorMobField);
        leftPanel.add(new JLabel("Doctor M. Number  : "));
        JTextField doctorMedicalNoField = new JTextField(10);
        leftPanel.add(doctorMedicalNoField);
        leftPanel.add(new JLabel("Doctor Specialization  : "));
        JTextField doctorSpecField = new JTextField(10);
        leftPanel.add(doctorSpecField);


        JPanel middlePane = new JPanel(new GridLayout(0, 2, 10, 10));
        JButton selectPatient = new JButton("Select OLD Patient");
        middlePane.add(selectPatient);

        middlePane.add(new JLabel(""));
        middlePane.add(new JLabel("Patient first Name : "));
        JTextField patientFNameField = new JTextField(10);
        middlePane.add(patientFNameField);
        middlePane.add(new JLabel("Patient last Name  : "));
        JTextField patientLNameField = new JTextField(10);
        middlePane.add(patientLNameField);
        middlePane.add(new JLabel("Patient DOB (dd/mm/yyyy) :"));
        JTextField patientDobField = new JTextField(10);
        middlePane.add(patientDobField);
        middlePane.add(new JLabel("Patient Mobile Number  : "));
        JTextField patientMobField = new JTextField(10);
        middlePane.add(patientMobField);
        middlePane.add(new JLabel("Select Time : "));


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());

        JSpinner spinner = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);

        spinner.setEditor(editor);

        JPanel content = new JPanel();
        content.add(spinner);

        middlePane.add(content);

        middlePane.add(new JLabel("No of hours : "));
        JTextField hoursField = new JTextField(10);
        middlePane.add(hoursField);

        middlePane.add(new JLabel("Patient Id  : "));
        JTextField patientIdField = new JTextField(10);
        patientIdField.setText(getPatientId());

        selectPatient.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame patientFrame = new JFrame("Patient Table");
                patientFrame.setSize(600,200);
                String[] colums = {"First Name","Last Name","DOB","Mobile Number","Id",};
                DefaultTableModel tableModel = new DefaultTableModel(colums,5);
                JTable patientTable = new JTable(tableModel);
                JScrollPane tablePane = new JScrollPane(patientTable);
                tableModel.setRowCount(0);
                Object[] rowData = new Object[5];

                for (Patient patient:patientArrayList) {

                    rowData[0] = patient.getName();
                    rowData[1] = patient.getSurname();
                    // Simple Date Format for format the output to DD/YY/MMM format.
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    rowData[2] = sdf.format(patient.getDateOfBirth());
                    rowData[3] = patient.getMobileNumber();
                    rowData[4] = patient.getPatientId();


                    tableModel.addRow(rowData);
                }

                patientTable.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        patientFrame.dispose();
                        costField.setText("25");
                        String id = (String) patientTable.getValueAt(patientTable.getSelectedRow(),4);
                        for (Patient patient:patientArrayList) {
                            if(id.trim().equalsIgnoreCase(patient.getPatientId())){
                                patientIdField.setText(patient.getPatientId());
                                patientFNameField.setText(patient.getName());
                                patientLNameField.setText(patient.getSurname());
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                patientDobField.setText(sdf.format(patient.getDateOfBirth()));
                                patientMobField.setText(patient.getMobileNumber());

                            }
                        }


                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                patientFrame.add(tablePane);
                patientFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                patientFrame.setLocationRelativeTo(null);
                patientFrame.setVisible(true);
            }
        });
        middlePane.add(patientIdField);


        JPanel rightPanel = new JPanel(new GridLayout(0, 1, 10, 3));
        rightPanel.add(new JLabel("Date"));
        JDateChooser dateChooser;
        rightPanel.add(dateChooser = new JDateChooser());
        JButton availabilityButton;
        rightPanel.add(availabilityButton = new JButton("Check Availability"));
        availabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Doctor doctor=null;
                String doctorId = doctorMedicalNoField.getText().trim();
                for (Doctor d:doctors) {
                    if(doctorId.equalsIgnoreCase(d.getMedicalLicenceNumber())){
                        doctor=d;
                        break;
                    }
                }

                if(doctorId.equals("")){
                    JOptionPane.showMessageDialog(null,"Please Select Doctor");
                    return;
                }
                try{
                    Date consultationDate = dateChooser.getDate();
                    if(consultationDate==null){
                        JOptionPane.showMessageDialog(null,"Please Check date");
                        return;
                    }
                    boolean isDoctorBusy = isDoctorBusy(doctor,consultationDate);
                    if(isDoctorBusy){
                        JOptionPane.showMessageDialog(null,"Doctor is Busy");
                    }else {
                        JOptionPane.showMessageDialog(null,"Doctor is Available");
                    }

                }catch (Exception ignored){}




            }
        });
        rightPanel.add(new JLabel("Cost Per Hour"));

        rightPanel.add(costField = new JTextField(10));
        costField.setEditable(false);
        costField.setText("15");
        rightPanel.add(new JLabel("Notes"));
        JTextField notesField;
        rightPanel.add(notesField = new JTextField(10));
        JButton encryptionButton = new JButton("Upload Image");
        rightPanel.add(encryptionButton);
        encryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f=new JFrame();
                f.setTitle("Image Encryption Tool");
                f.setSize(400,320);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                JLabel label1 = new JLabel();
                label1.setText("Enter 123 To Encrypt/ Decrypt Image");
                label1.setFont(new Font("Roboto",Font.BOLD,17));

                JTextField textField=new JTextField(10);
                textField.setFont(new Font("Arial",Font.BOLD,13));


                JButton button=new JButton();
                button.setText("Select Image");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser=new JFileChooser();
                        fileChooser.showOpenDialog(null);
                        File file=fileChooser.getSelectedFile();
                        //file FileInputStream
                        try
                        {
                            FileInputStream fis=new FileInputStream(file);

                            byte []data=new byte[fis.available()];
                            fis.read(data);
                            int i=0;
                            for(byte b:data)
                            {
                                //System.out.println(b);
                                data[i]= b;
                                i++;
                            }

                            FileOutputStream fos=new FileOutputStream(file);
                            fos.write(data);
                            fos.close();
                            fis.close();

                            JFrame img = new JFrame("Image");
                            img.setSize(300,300);
                            JPanel heading = new JPanel();
                            heading.add(new JLabel("Skin Image"));
                            img.add("North",heading);
                            ImageIcon skinIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Skin/Skin3.jpg")));
                            JLabel mylabel = new JLabel(skinIcon);
                            img.add("Center",mylabel);
                            img.setLocationRelativeTo(null);
                            img.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            img.setVisible(true);

                            JOptionPane.showMessageDialog(null, "Done! Go and Check your selected image path!");


                        }catch(Exception exception)
                        {
                            exception.printStackTrace();
                        }

                    }
                });


                Label l1,l2,l3;
                l1=new Label("To Decrypt image give the same key as given on Encryption time!");
                l1.setBounds(50,100, 100,20);
                l2=new Label("    And select the same image!");
                l2.setBounds(110,150, 400,20);
                l3=new Label("                                                                                                            ");
                l3.setBounds(50,180, 50,20);

                JButton backBtn = new JButton();
                backBtn.setText("Back");

                button.addActionListener(t->{
                    String text=textField.getText();
                    int temp=Integer.parseInt(text);
                    operate(temp);
                });

                backBtn.setBounds(20,300,60,30);
                backBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.dispose();

                    }
                });

                f.setLayout(new FlowLayout());
                f.add(label1);
                f.add(textField);
                f.add(button);
                f.add(l1);
                f.add(l2);
                f.add(l3);

                f.add(backBtn);
                f.setVisible(true);
            }
            public void operate(int key)
            {

                JFileChooser fileChooser=new JFileChooser();
                fileChooser.showOpenDialog(null);
                File file=fileChooser.getSelectedFile();
                //file FileInputStream
                try
                {

                    FileInputStream fis=new FileInputStream(file);

                    byte []data=new byte[fis.available()];
                    fis.read(data);
                    int i=0;

                    for(byte b:data)
                    {
                        //System.out.println(b);
                        data[i]=(byte)(b^key);
                        i++;
                    }

                    FileOutputStream fos=new FileOutputStream(file);
                    fos.write(data);
                    fos.close();
                    fis.close();
                    JFrame img = new JFrame("Image");
                    img.setSize(300,300);
                    JPanel heading = new JPanel();
                    heading.add(new JLabel("Skin Image"));
                    img.add("North",heading);
                    ImageIcon skinIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Skin/Skin3.jpg")));
                    JLabel mylabel = new JLabel(skinIcon);
                    img.add("Center",mylabel);
                    img.setLocationRelativeTo(null);
                    img.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    img.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Done! Go and Check your selected image path!");

                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }


        });
        JButton addConsultationButton;
        rightPanel.add(addConsultationButton = new JButton("Add Consultation"));
        JButton clearButton;
        middlePane.add(clearButton = new JButton("Clear"));
        addConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Doctor doctor=null;
                Patient patient;
                String doctorId = doctorMedicalNoField.getText().trim();
                for (Doctor d:doctors) {
                    if(doctorId.equalsIgnoreCase(d.getMedicalLicenceNumber())){
                        doctor=d;
                        break;
                    }
                }

                Date dob;

                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dob = format.parse(patientDobField.getText());
                    patient = new Patient(patientFNameField.getText(), patientLNameField.getText(), dob, patientMobField.getText(), patientIdField.getText());
                    Date consultationDate = dateChooser.getDate();
                    Date date = (Date) spinner.getValue();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int hours = Integer.parseInt(hoursField.getText());
                    if(isDoctorBusy(doctor,consultationDate)) {
                        JOptionPane.showMessageDialog(null,"Doctor is Busy");
                        doctor=getRandomDoctor(consultationDate);

                    }
                    int cost = 25;
                    if(!isPatientAlreadyExists(patient)){
                        patientArrayList.add(patient);
                        cost = 15;
                    }
                    Consultation consultation = new Consultation(doctor, patient, consultationDate,date,hours, cost * hours, notesField.getText());
                    WestminsterSkinConsultationManager.consultationArrayList.add(consultation);

                    JOptionPane.showMessageDialog(null,"Doctor Added");


                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Please enter Patient Correct DOB");

                }

            }
        });

        doctorTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = (String) doctorTable.getValueAt(doctorTable.getSelectedRow(),5);
                for (Doctor doctor:doctors) {
                    if(id.trim().equalsIgnoreCase(doctor.getMedicalLicenceNumber())){
                        doctorFNameField.setText(doctor.getName());
                        doctorLNameField.setText(doctor.getSurname());
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        doctorDobField.setText(sdf.format(doctor.getDateOfBirth()));
                        doctorMobField.setText(doctor.getMobileNumber());
                        doctorMedicalNoField.setText(doctor.getMedicalLicenceNumber());
                        doctorSpecField.setText(doctor.getSpecialisation());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

        });
        JButton viewBooking = new JButton("View Booking");
        rightPanel.add(viewBooking);
        viewBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                viewBookingsFrame = new JFrame("Consultation Table");
                viewBookingsFrame.setSize(1100,375);
                String[] colums = {"Patient Id","Date","Time","Patient's Name","Patient's Surname","Patient's DOB","Patient tel No","No of Hours","Doctor's Name","Specialization","Cost","Note"};
                DefaultTableModel tableModel = new DefaultTableModel(colums,11);
                JTable patientTable = new JTable(tableModel);
                JScrollPane tablePane = new JScrollPane(patientTable);
                tableModel.setRowCount(0);
                Object[] rowData = new Object[12];

                for (Consultation consultation:consultationArrayList) {

                    rowData[0] = consultation.getPatient().getPatientId();
                    // Simple Date Format for format the output to DD/YY/MMM format.
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    rowData[1] = sdf.format(consultation.getDateTime());
                    // Simple Date Format for format the output to HH:mm:ss format.
                    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
                    rowData[2] = time.format(consultation.getTime());
                    rowData[3] = consultation.getPatient().getName();
                    rowData[4] = consultation.getPatient().getSurname();
                    rowData[5] = consultation.getPatient().getDateOfBirth();
                    rowData[6] = consultation.getPatient().getMobileNumber();
                    rowData[7] = consultation.getHours();
                    rowData[8] = consultation.getDoctor().getName();
                    rowData[9] = consultation.getDoctor().getSpecialisation();
                    rowData[10] = consultation.getCost();
                    rowData[11] = consultation.getNotes();
                    tableModel.addRow(rowData);
                }

                JButton backButton = new JButton("Main Menu");
                viewBookingsFrame.add(backButton);
                backButton.setBounds(410,270,110,25);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewBookingsFrame.dispose();
                        frame.setVisible(true);

                    }
                });

                JButton quit = new JButton("Quit");
                viewBookingsFrame.add(quit);
                quit.setBounds(580,270,110,25);
                quit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewBookingsFrame.dispose();
                    }
                });

                JLabel lblHeading = new JLabel("                                              " +
                        "                                                        Westminster Skin Consultation Bookings");
                lblHeading.setFont(new Font("Arial",Font.BOLD,14));
                viewBookingsFrame.getContentPane().add(lblHeading,BorderLayout.PAGE_START);
                viewBookingsFrame.add(tablePane);
                viewBookingsFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                viewBookingsFrame.setLocationRelativeTo(null);
                viewBookingsFrame.setVisible(true);



            }
        });

        JButton backButton = new JButton("Main Menu");
        rightPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                frame.setVisible(true);

            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patientFNameField.setText("");
                patientLNameField.setText("");
                patientDobField.setText("");
                patientIdField.setText(getPatientId());
                patientMobField.setText("");
                doctorFNameField.setText("");
                doctorLNameField.setText("");
                doctorDobField.setText("");
                doctorMobField.setText("");
                doctorMedicalNoField.setText("");
                doctorSpecField.setText("");
                dateChooser.setDate(null);
                notesField.setText("");
                costField.setText("15");
                hoursField.setText("");

                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                model.setValue(calendar.getTime());
            }
        });

        panel.add(leftPanel);
        panel.add(middlePane);
        panel.add(rightPanel);

        thisFrame.add("South",panel);
        thisFrame.setLocationRelativeTo(null);
        //return frame;

    }
    public boolean isDoctorBusy(Doctor doctor,Date date){
        if(WestminsterSkinConsultationManager.consultationArrayList.size()>0) {
            for (Consultation consult : WestminsterSkinConsultationManager.consultationArrayList) {
                if (consult.getDoctor().equals(doctor)) {
                    if (consult.getDateTime().equals(date)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    public Doctor getRandomDoctor(Date date){
        ArrayList<Doctor> busyDoctors = new ArrayList<>();
        ArrayList<Doctor> temp = new ArrayList<>();
        SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy");
        String date1 = stm.format(date);
        for (Consultation consultation:consultationArrayList) {
            if(consultation.getDateTime().equals(date1)){
                busyDoctors.add(consultation.getDoctor());
            }
        }
        temp.addAll(doctors);
        for (Doctor d: busyDoctors) {
            temp.remove(d);
        }
        if(temp.size()==0){
            return null;
        }
        return temp.get(0);

    }
    public String getPatientId(){
        return (patientArrayList.size()+10001)+"";
    }
    public boolean isPatientAlreadyExists(Patient patient){
        if(patientArrayList.size()==0){
            return false;
        }
        for (Patient p:patientArrayList) {
            if(p.getPatientId().equals(patient.getPatientId())){
                return true;
            }
        }
        return false;
    }



    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
                                             double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int)
                    (tablePreferredWidth * (percentages[i] / total)));
        }
    }




}
