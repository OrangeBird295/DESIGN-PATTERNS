# SOLID Principles
### S - Single responsibility principle. 

`
Class 1 Class ควรมีหน้าที่แค่ 1 หน้าที่
`
- A class should only have one responsibility
- This separates different tasks, problems, and concerns into different classes handling

##


### O - Open closed principle. 
`
ควรมี Interface ให้ Class สามารถเรียกใช้ เพื่ออนาคตมีการเพื่มเปลี่ยนแปลง Class
`
- Classes should be open for extension
- Classes should be closed for modification

##

### L - Liskov substitution principle.
`
เมื่อเรา inherit class มาก็จะมีทั้ง 1.Class แม่(Base Class)  / 2.Class ลูก(Sub Class) -> Sub Class สามารถแทนที่ Base Class ได้ 
`

**นิยามคือ:** กระบวณการใดๆ ก็ตามของ Class ลูก. แล้วไปเปลี่ยนสิ่งที่เป็นอยู่ของ Class แม่ ค่าจึงผิด

**ตัวอย่างคือ:** Class ผ.ช. มีความสามารถคือ กินเช้า, กินเที่ยง, กินเย็น แต่เมื่อถูก ----Inherit--> แล้วเป็น Class พระจะกินได้เพียงแค่ เช้ากับเที่ยง    

เพราะฉะนั้น ผ.ช. = พระ ดังนั้นเมื่อสั่งให้ ผ.ช. กินข้าวแล้วนับก็จะผิดพลาด
- A base type should able to be substituted by a subtype without altering the correctness of the program

##

### I - Interface segregation principle.
`
คล้าย [S] Interface 1 Interface ต้องกำหนดความสามารถแค่จุดประสงค์เดียวไม่ใช่หลายจุดประสงค์
`

- Onc interface should serve a specific purpose, not several general purposes
- If there are too much in as interface, separate it
- Remember **YAGNI**: You Aren't Going to Need It

##

### D - Dependency inversion principle. 
`Main class or Class process ไม่ควรขึ้นต่อ Low-level Class`

`ควรจะทำให้หน่วยของซอฟต์แวร์ขึ้นอยู่กับ abstract class หรือ interface แทน ที่จะขึ้นอยู่กับ concrete class`

- High-level modules should not depend upon low-level ones, both should depend upon abstractions 
- Abstractions should not depend upon details, details should depend upon abstractions

##

### COMPOSITE ( Manage Group )
`การออกแบบโครงสร้าง Object `

**ปัญหา:** object ที่มีการ Group กันหรือซ้อนกันทำให้แก้ได้ยาก เช่น การ Implement ซ้อนกันเรื่อยๆ  

**แก้โดย** จัดการตัว Object ที่ซ้อนกันเป็นลำดับชั้นลงมาเรื่อยๆ จะเป็นการกำหนดการทำงานหรือสั่งทั้ง Group โดยครั้งเดียว

**Idea:** มอง Object ให้เป็น 2 ประเภท 

 - 1.Leaf ไม่มีตัวซ้อนต่อ  
 - 2.Composite มีตัวซ้อนต่อ -> เป็นแค่โครงสร้างหรือกลุ่มของ Object เมื่อใดมีคำสั่ง จะส่งคำสั่งไปยังตัวประกอบทุกตัว(Leaf)   [Composite อาจซ้อนด้วย Composite อีกทีได้]

**ข้อดี**

- สามารถจัดการ Object ที่ถูก Group ได้ง่าย
- Client Interact รูปแบบเดียว
- Add Composite ง่าย

**ข้อเสีย**

- ขัดกับ SOLID ตัวที่ [ I ]
- ใส่ข้อจำกัดอย่างเฉพาะเจาะจงหรืออย่างใดอย่างหนึ่งยาก

##

### STRATEGY ( Split The Class for use Different f(x) )
`พฤติกกรมของ Object `

**ปัญหา:** Object บาง Object มีการทำงานไม่หมือนกันทุกตัวจึงไม่สามารถใช้การ Implement หรือการใช้ f(x) กับทุกตัวได้

**แก้โดย** นำ Algorithm ที่ต้องการ Implement ทำเป็น Class แยกอัน ซึ่งเมื่อเวลาจะเรียกใช้ก็ให้นำตัว Object ไปเรียกที่ตัว Class นั้นๆ แทน (Object ไม่จำเป็นต้องรู้ว่า Algorithm เปลี่ยนแปลงยังไงมีหน้าที่แค่เรียกก็พอ) ซึ่ง Class ที่แยกออกมาจะเรียกว่า STRATEGY

**ข้อดี**

- สามารถเพิ่ม STRATEGY ได้เรื่อยๆ ตราบใดที่มีการเรียกใช้งานแบบเดียวกัน

**ข้อเสีย**

- Maintain code ยาก เรื่องจากต้องไปโยงกับตัว Context หรือใน Code ก็คือ TextComposite, TextLeaf
- เราต้องรู้ว่ามี STRATEGY อะไรให้เรียกใช้บาง
- เรียกข้าม Class บ่อยจึงเกิด Overhead(เปลืองทรัพยากร)
- จำนวน Object เพื่มตาม STRATEGY
<<<<<<< HEAD
 
##

### DECORATOR
`การออกแบบโครงสร้าง Object `

**Idea:** ถ้าเกิดต้องการเพิ่มความสามารถหรือส่วนประกอบไปใน Object แทนที่เราจะเพิ่มไปตรงๆ เราสามารถสร้างอะไรขึ้นมาห่อหุ้ม Object นั้นๆ แล้วเปลี่ยน Object ตัวนั้นให้เป็น Object ตัวใหม่ (เปลี่ยนแค่ภายนอก)

**ข้อดี** 

- สามารถสร้างความสามารถมาห่อกี่ชั้นก็ได้ จึงทำให้มีความยืดหยุ่่น
=======

##

### DECORATOR ( WRAPPER )
 `การออกแบบโครงสร้าง Object `

**Idea:** ถ้าเกิดต้องการเพิ่มความสามารถหรือส่วนประกอบลงไปใน Object แทนที่เราจะเพิ่มไปโดยตรง เราสามารถสร้างอะไรมาห่อหุ้ม Object นั้นแล้วเปลี่ยน Object ตัวนั้นให้เป็น Object ใหม่

**ทำเพื่อ** 

- เพิ่มความสามารถหรืออะไรบางอย่างลงไปใน Object โดยที่เราไม่ต้องการที่จะเปลี่ยนแปลงหรือแก้ไข Object นั้นๆ

**ข้อดี** 

- *ห่อกี่ชั้นก็ได้* จึงมีความยืดหยุ่นในการเพิ่มความสามารถ

**ข้อเสีย** 

- เมื่อเขียนเป็น Object จะทำให้ Object ย่อยมากๆ ( ยิบย่อย )
- Object ที่ทำการ Decoretor แล้วนั้นไม่ถือว่าเป็น Object ตัวเดียวกัน

![alt text](https://github.com/OrangeBird295/DESIGN-PATTERNS/tree/main/images/Dif_Deco&Strate.png?raw=true)

##

### ABSTRACT FACTORY ()
 `การสร้าง Object `

**Idea:** เป็นการสร้าง Object ใดๆ ก็ตามที่เราไม่รู้ความสามารถในการสร้างเป็นอย่างไร เพราะฉะนั้นเราจะไปเรียกใช้ object ตัวนั้นที่เขารู้ว่า เขาจะสร้าง Object ตามที่เราต้องการอย่างไร
    
-  `Object ที่เราต้องการสร้าง -> Product`
-  `Object ที่รู้การสร้าง -> Factory`

**ต่อ** Factory รู้ว่าผลิต (A, B, C) ได้. Factory ก็จะผลิตแค่ (A, B, C) เท่านั้น เช่น แทนด้วย ปากกาเมจิ, ปากกาลูกลื่น, ปากกาไวท์บอด

โดยสมมุติว่ามี Factory 2 ที่แต่ละทีก็จะรู้วิธีการผลิตทั้ง 3 ปากกา โดย
- Factory1 ผลิตสีแดง
- Factory2 ผลิตสีน้ำเงิน

**ข้อดี**

- รู้วิธีการผลิตที่แน่นอน และเปลี่ยนคุณสมบัติเพียงเล็กน้อย

**ข้อเสีย**

- ถ้าเราต้องการผลิต Product ชนิดใหม่ ทุกๆ โรงงานต้องเรียนรู้วิธีการผลิต Prodcut ใหม่


>>>>>>> a4ef6d529cd0262e3fde30d602801fd9739b0729
