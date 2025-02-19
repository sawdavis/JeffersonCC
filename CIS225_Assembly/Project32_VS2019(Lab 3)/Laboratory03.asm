; Sawyer Davis - Laboratory 02
;Date: 02/27/2024
;Due:  03/05/2024

COMMENT *
This program computes a midterm average. The program will compute a lab, quiz, homework, and exam average.
The program will then weigh the averages and finally calculate the midterm average.
*

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	labGrades	word	10, 9, 8, 7	
	labSize		byte	($ - labGrades) / TYPE labGrades
	ALIGN 2
	quizGrades	word	9, 8
	hwGrades	word	8, 9
	exam		word	122
	average		word	?, ?, ?, ?
	wAverage	word	?, ?, ?, ?
	quizSize	byte	LENGTHOF quizGrades
	ALIGN 2
	hwSize		byte	SIZEOF hwGrades / TYPE hwGrades
	ALIGN 2
	midGrade	word	?
.code
main proc
	mov		edi, OFFSET average
;Calculate Lab Average
	movzx	ecx, labSize
	mov esi, OFFSET labGrades
	mov ax, 0
L1: mov bx, [esi]
	add ax, bx
	add esi, Type labGrades
	loop L1
	movzx bx, [labSize]
	mov cx, 10
	mul cx
	mov edx, 0
	div bx
	mov [edi], ax
	add edi, TYPE average

;Calcuate Quiz Average
	movzx	ecx, quizSize
	mov esi, OFFSET quizGrades
	mov ax, 0
L2: mov bx, [esi]
	add ax, bx
	add esi, Type quizGrades
	loop L2
	movzx bx, [quizSize]
	mov cx, 10
	mul cx
	mov edx, 0
	div bx
	mov [edi], ax
	add edi, TYPE average

;Calculate Hw Avergae
	movzx	ecx, hwSize
	mov esi, OFFSET hwGrades
	mov ax, 0
L3: mov bx, [esi]
	add ax, bx
	add esi, Type hwGrades
	loop L3
	movzx bx, [hwSize]
	mov cx, 10
	mul cx
	mov edx, 0
	div bx
	mov [edi], ax
	add edi, TYPE average

;Calculate Exam Average
	mov ax, exam
	mov bx, 100
	mul bx
	mov cx, 127
	div cx
	mov [edi], ax

;Weigh Averages
	mov	esi, OFFSET average
	mov	edi, OFFSET wAverage
	mov ax,	[esi]
	mov bx, 35
	mul bx
	add ax, 50
	mov [edi], ax
	add	esi, TYPE average
	add	edi, TYPE wAverage

	mov ax,	[esi]
	mov bx, 10
	mul bx
	add ax, 50
	mov [edi], ax
	add	esi, TYPE average
	add	edi, TYPE wAverage

	mov ax,	[esi]
	mov bx, 10
	mul bx
	add ax, 50
	mov [edi], ax
	add	esi, TYPE average
	add	edi, TYPE wAverage

	mov ax,	[esi]
	mov bx, 25
	mul bx
	add ax, 50
	mov [edi], ax
	add	esi, TYPE average
	add	edi, TYPE wAverage

;Calculate Midterm Average
	mov	esi, OFFSET wAverage
	mov ax, [esi]
	add esi, TYPE wAverage
	add ax, [esi]
	add esi, TYPE wAverage
	add ax, [esi]
	add esi, TYPE wAverage
	add ax, [esi]
	mov cx, 80
	mov edx, 0
	div cx
	mov midGrade, ax

	invoke ExitProcess,0
main endp
end main