; Sawyer Davis - Laboratory 06
;Date: 03/19/2024
;Due:  03/26/2024

include Irvine32.inc

COMMENT *
This program will allow a user to convert their inputed number to a base of 2-16 of their choosing. They can do this as long as they would like and converted number will be
displayed each time.
*

.386
; .model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	base			sword	?
	remainder		word	1000 DUP (?)
	numRemainders	word	0
	number			sword	?
	choice			byte	?
	msg1			byte	"Enter a number: ", 0
	msg2			byte	"Enter a number (MUST BE POSITIVE): ", 0
	msg3			byte	"Enter your base: ", 0
	msg4			byte	"Enter your base (MUST BE BETWEEN 2-16 INCLUSIVE): ", 0
	msg5			byte	"Remainder: ", 0
	msg6			byte	"Would you like to continue? (Y/N): ", 0


.code
main proc
top:	call	getNumber
		call	getBase
		call	calculateRemainders
		call	displayRemainders
ask:	mov		edx, OFFSET msg6
		call	WriteString
		call	ReadChar
		call	WriteChar
		call	Crlf
		cmp		al, 'Y'
		je		quit
		cmp		al, 'N'
		je		quit
		jmp		ask
quit:
		mov		esi, 4218880
		mov		ecx, 256
		mov		ebx, 1
		call	DumpMem
invoke ExitProcess,0
main endp

;Get Number
getNumber proc
		mov		edx, OFFSET msg1
		call	WriteString
		call	ReadInt
		cmp		ax, 0
		jge		pos
		jl		lw
lw:		
		mov		edx, OFFSET msg2
		call	WriteString
		call	ReadInt
		cmp		ax, 0
		jge		pos
		jl		lw
pos:	
		mov		number, ax
		ret		
getNumber endp

;Get Base
getBase proc
		mov		edx, OFFSET msg3
		call	WriteString
		call	ReadInt 
		jmp		c1 
eI:		
		mov		edx, OFFSET msg4
		call	WriteString
		call	ReadInt
c1:		
		cmp		ax, 2
		jl		eI
		cmp		ax, 16
		jg		eI
		mov		base, ax
	ret
getBase endp

;Calculate Remainders
calculateRemainders proc
		mov		numRemainders, 0
		mov		esi, OFFSET remainder
		mov		ax, number
		mov		bx, base
work:
		mov		edx, 0
		div		bx
		mov		[esi],	dx
		inc		numRemainders
		add		esi,	TYPE remainder
		cmp		ax,	0
		jnz		work	
		sub		esi, TYPE remainder
	ret
calculateRemainders endp

;Display Remainders
displayRemainders proc
		mov		edx, OFFSET msg5
		movzx	ecx, numRemainders
L1:		
		mov		ax, [esi]
		cmp		ax, 9
		jg		C1
		;call	WriteDec
		;sub		esi, TYPE remainder
		jmp		L2
C1:
		cmp		ax, 10
		jne		C11
		mov		al, 'A'
		jmp		WC 
C11:	
		cmp		ax, 11
		jne		C12
		mov		al, 'B'
		jmp		WC
C12:	
		cmp		ax, 12
		jne		C13
		mov		al, 'C'
		jmp		WC
C13:	
		cmp		ax, 13
		jne		C14
		mov		al, 'D'
		jmp		WC
C14:	
		cmp		ax, 14
		jne		C15
		mov		al, 'E'
		jmp		WC
C15:	
		mov		al, 'F'
		jmp		WC

WC: 	call	WriteChar
		jmp		ed
L2:		call	WriteDec
ed:		sub		esi, TYPE remainder
		Loop L1
		call	Crlf
	ret
displayRemainders endp

end main