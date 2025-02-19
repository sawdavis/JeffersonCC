; Sawyer Davis - Laboratory 03
;Date: 03/12/2024
;Due:  03/19/2024

include Irvine32.inc

COMMENT *
This program will allow a student to enter their name and test answers. The program will determine the amount of right and wrong answers and will calculate a numerical and
letter grade.
*

.386
; .model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	userName	byte	80 DUP(?)
	answers		byte	12 DUP(?)
	key			byte	'A', 'B', 'C', 'D', 'B', 'C', 'D', 'A', 'C', 'B', 'D', 'A'
	numC		byte	0
	numI		byte	0
	grade		word	?
	letter		byte	?
	msg1		byte	"Enter your name: ", 0
	msg2		byte	"Enter your answer(A-D): ", 0
	msg3		byte	"Enter your ANSWER(MUST BE A-D): ", 0
	msg4		byte	"Student Name: ", 0
	msg5		byte	"Number Correct: ", 0
	msg6		byte	"Number Incorrect: ", 0
	msg7		byte	"Grade: ", 0
	msg8		byte	"Letter Grade: ", 0

.code
main proc
	call	getName
	call	getAnswers
	call	gradeAnswers
	call	calPer
	call	calLetter
	call	disSum

invoke ExitProcess,0
main endp

;Get Student Name
getName proc	
	mov		edx, OFFSET msg1
	call	WriteString
	mov     edx, OFFSET userName
	mov		ecx, LENGTHOF userName	
	call	ReadString
getName endp

;Get Valid Answers
getAnswers proc
		mov		esi, OFFSET answers
		mov		ecx, LENGTHOF answers
L1:		mov		edx, OFFSET msg2
		call	WriteString
		call	ReadChar
		call	WriteChar
		call	Crlf
		jmp		c1
eI:		; prompt them with error msg
		mov		edx, OFFSET msg3
		call	WriteString
		call	ReadChar
		call	WriteChar
		call	Crlf
c1:		cmp		al, 'A'
		jl		eI
		cmp		al, 'D'
		jg		eI
		mov		[esi], al
		add		esi, TYPE answers
		loop	L1
getAnswers endp

;Grade Each Answer
gradeAnswers proc
		mov		esi, OFFSET key
		mov		edi, OFFSET answers
		mov		ecx, LENGTHOF answers   
L2:		mov     al, [esi]
		mov     bl, [edi]
		cmp     al, bl
		JB      I1
		cmp     al, bl
		JA      I1
		Jmp     C1
I1:		inc		numI
		Jmp     R1
C1:		inc		numC
		Jmp     R1
R1:		add     esi, TYPE key
		add     edi, TYPE answers
		loop	L2
gradeAnswers endp

;Calculate Percentage
calPer proc
		movzx	ax, numC
		mov		bx, 100
		mul		bx
		add		ax, 5
		mov		edx, 0
		mov		bx, LENGTHOF answers
		div		bx
		mov		grade, ax
calPer endp

;Determine Letter Grade
calLetter proc
		mov     ax, grade
		cmp     ax, 90
		JB      LGA
		cmp     ax, 100
		JA      LGA
		mov     letter, 'A'
		Jmp     R1
LGA:
		cmp     ax, 80
		JB      LGB
		cmp     ax, 90
		JAE     LGB
		mov     letter, 'B'
		Jmp     R1
LGB:
		cmp     ax, 70
		JB      LGC
		cmp     ax, 80
		JAE     LGC
		mov     letter, 'C'
		Jmp     R1
LGC:
		cmp     ax, 65
		JB		LGD 
		cmp     ax, 70
		JAE     LGD
		mov     letter, 'D'
		Jmp     R1
LGD:
		mov     letter, 'F'
R1:	
calLetter endp

;Display Summary
disSum proc
		mov		edx, OFFSET msg4
		call	WriteString
		mov		edx, OFFSET userName
		call	WriteString
		call	Crlf
		mov		edx, OFFSET msg5
		call	WriteString
		mov		al, numC
		call	WriteDec
		call	Crlf
		mov		edx, OFFSET msg6
		call	WriteString
		mov		al, numI
		call	WriteDec
		call	Crlf
		mov		edx, OFFSET msg7
		call	WriteString
		mov		ax, grade
		call	WriteDec
		call	Crlf
		mov		edx, OFFSET msg8
		call	WriteString
		mov		al, letter
		call	WriteChar
		call	Crlf
		mov		esi, 4218880
		mov		ecx, 256
		mov		ebx, 1
		call	DumpMem
disSum endp

end main