; Sawyer Davis - Laboratory 07
;Date: 03/19/2024
;Due:  03/26/2024

include Irvine32.inc

COMMENT *
This program will allow a user to keep track of their checking account. Using keyed in data, the program will generate a summary of of credits and
debits on the account.
*

.386
; .model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	userName	byte	80 DUP(?)
	street		byte	80 DUP(?)
	csz			byte	80 DUP(?)
	balance		sdword	?
	dateWid	= 11
	date		byte	20 DUP (dateWid DUP (?))
	descWid		=	80
	desc		byte	20 DUP (descWid DUP (?))
	tType		byte	20 DUP (?)
	numT		byte	0
	amount		sdword	20 DUP (?)
	choice		byte	?
	msg1		byte	"Enter your name: ", 0
	msg2		byte	"Enter your Street Address: ", 0
	msg3		byte	"Enter your City/State/Zip: ", 0
	msg4		byte	"Enter your starting balance: ", 0
	msg5		byte	"Enter your starting balance(MUST BE POSITIVE): ", 0
	msg6		byte	"Enter the transaction date: ", 0
	msg7		byte	"Enter the transaction description: ", 0
	msg8		byte	"Enter the transaction category: ", 0
	msg11		byte	"Enter the transaction category(MUST BE C, D, c, d): ", 0
	msg9		byte	"Enter the transaction amount in pennies: ", 0
	msg12		byte	"Enter the transaction amount in pennise(MUST BE POSIVITE): ", 0
	msg10		byte	"Are you done entering transactions (Y/N): ", 0
	msg13		byte	"Starting Balance: ", 0
	msg14		byte	"Date: ", 0
	msg15		byte	"Desciption: ", 0
	msg16		byte	"Amount: ", 0
	msg17		byte	"Current Balance: ", 0
	msg18		byte	"Final Balance: ", 0

.code
main proc
		call	getAccInf
		call	loadEachTran
		call	displayReport
invoke ExitProcess,0
main endp

;Get Account Info
getAccInf proc
		mov		edx, OFFSET msg1
		call	WriteString
		mov     edx, OFFSET userName
		mov		ecx, LENGTHOF userName	
		call	ReadString
		mov		edx, OFFSET msg2
		call	WriteString
		mov     edx, OFFSET street
		mov		ecx, LENGTHOF street	
		call	ReadString
		mov		edx, OFFSET msg3
		call	WriteString
		mov     edx, OFFSET csz
		mov		ecx, LENGTHOF csz	
		call	ReadString
		mov		edx, OFFSET msg4
		call	WriteString
		call	ReadInt
		cmp		eax, 0
		jge		pos
		;jl		lw
lw:		
		mov		edx, OFFSET msg5
		call	WriteString
		call	ReadInt
		cmp		ax, 0
		jge		pos
		jl		lw
pos:	
		mov		balance, eax
	ret
getAccInf endp

;Load Each Transaction
loadEachTran proc uses eax ebx ecx edx esi edi
		mov		esi, OFFSET date
		mov		edi, OFFSET desc
		mov		ebx, OFFSET tType
		mov		ecx, OFFSET amount
		.WHILE(choice != 'Y' && numT < 20)
			mov		edx, OFFSET msg6
			call	WriteString
			mov		edx, esi
			push	ecx
			mov		ecx, dateWid
			call	ReadString
			pop		ecx
			add		esi, dateWid
			mov		edx, OFFSET msg7
			call	WriteString
			mov		edx, edi
			push	ecx
			mov		ecx, descWid
			call	ReadString
			pop		ecx
			add		edi, descWid
			mov		edx, OFFSET msg8
			call	WriteString
			call	ReadChar
			call	WriteChar
			call	Crlf
			.WHILE(al != 'C' && al != 'c' && al != 'D' && al != 'd')
				mov		edx, OFFSET msg11
				call	WriteString
				call	ReadChar
				call	WriteChar
				call	Crlf
			.ENDW
			mov		[ebx], al
			inc		ebx
			mov		edx, OFFSET msg9
			call	WriteString
			call	ReadInt
			.WHILE(SIGN?)
				mov		edx, OFFSET msg12
				call	WriteString
				call	ReadInt
			.ENDW
			mov		[ecx], ax
			add		ecx, TYPE amount
			mov		edx, OFFSET msg10
			call	WriteString
			call	ReadChar
			call	WriteChar
			call	Crlf
			.WHILE(al != 'Y' && al != 'N')
				mov		edx, OFFSET msg10
				call	WriteString
				call	ReadChar
				call	WriteChar
				call	Crlf
			.ENDW
			mov		choice, al
			inc numT
		.ENDW
	ret
loadEachTran endp

;Display Report
displayReport proc
		call	displayHeading
		call	procEachTran
		call	dispEndBal
	ret
displayReport endp

;Display Heading
displayHeading proc
		mov		edx, OFFSET userName
		call	WriteString
		call	Crlf
		mov		edx, OFFSET street
		call	WriteString
		call	Crlf
		mov		edx, OFFSET csz
		call	WriteString
		call	Crlf
		mov		edx, OFFSET msg13
		call	WriteString
		mov		eax, balance
		call	toDec
		call	Crlf

	ret
displayHeading endp

;Process Each Transaction
procEachTran proc
		mov		esi, OFFSET date
		mov		edi, OFFSET desc
		mov		ebx, OFFSET tType
		mov		ecx, OFFSET amount
		;movzx	ecx, numT
L1:
		call	upBal
		call	tranSum
		add		esi, dateWid
		add		edi, descWid
		inc		ebx
		add		ecx, TYPE amount
		dec		numT
		cmp		numT, 0
		jnz		L1
	ret
procEachTran endp

;Display (Ending) Balance
dispEndBal proc
		mov		edx, OFFSET msg18
		call	WriteString
		mov		eax, balance
		call	toDec
	ret
dispEndBal endp

;Update Balance
upBal proc
		mov		eax, balance
		push	eax
		mov		eax, [ecx]
		pop		eax
		.if(BYTE PTR[ebx] == 'D' || BYTE PTR[ebx] == 'd')
			add		eax, [ecx]
			mov		balance, eax
		.endif
		.if(BYTE PTR[ebx] == 'C' || BYTE PTR[ebx] == 'c')
			sub		eax, [ecx]
			mov		balance, eax
		.endif
	ret
upBal endp

;Show Transaction Summary
tranSum proc
		mov		edx, OFFSET msg14
		call	WriteString
		mov		edx, OFFSET date
		call	WriteString
		call	Crlf
		mov		edx, OFFSET msg15
		call	WriteString
		mov		edx, edi
		call	WriteString
		call	Crlf
		mov		edx, OFFSET msg16
		call	WriteString
		mov		eax, [ecx]
		call	toDec
		call	Crlf
		mov		edx, OFFSET msg17
		call	WriteString
		mov		eax, balance
		call	toDec
		call	Crlf

	ret
tranSum	endp

;To Decimal
toDec proc
	;Decimal Conversion - Check with Jack
	;mov		eax, balance
	mov		edx, 0 
	push	ebx
	mov		ebx, 100
	div		ebx
	call	WriteDec
	mov		al, '.'
	call	WriteChar
	.if(edx < 10)
		mov ax, 0
		call WriteDec
	.endif
		mov	eax, edx
		call WriteDec
		pop	ebx
	ret
toDec endp
end main