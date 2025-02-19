; Sawyer Davis - Laboratory 08
;Date: 04/16/2024
;Due:  04/23/2024

include Irvine32.inc

COMMENT *
This program will compute the gross pay for an inputed number of plumbing jobs worked. The user will be able to input the hourly rate,
the hours of each specific job, and the multiplier code for the job. The gross pay will then be displayed to the user.
*

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	numJobs		word	?
	hourly		word	?
	hours		word	80 DUP(?)
	mult		word	80 DUP(?)
	totalHours	word	0
	grossPay	word	?
	msg1		byte	"Enter the number of visits: ", 0
	msg2		byte	"Enter hourly rate (in whole dollars): ", 0
	msg3		byte	"Enter the hours for this job: ", 0
	msg4		byte	"Enter the code for this job (1-nomral) (2-night/weekend): ", 0
	msg5		byte	"Your gross pay is $", 0
	msg6		byte	".", 0
.code
main proc
	push	OFFSET numJobs
	Call	GetNumVis
	add		esp, 4
	push	OFFSET hourly
	Call	GetHourPay
	add		esp, 4
	Call	GetHourWork
	Call	CalGrossPay
	push	grossPay
	Call	DispPay
	add		esp, 6
invoke ExitProcess,0
main endp

;Get Number of Visits
GetNumVis proc
	push	ebp
	mov		ebp, esp

	mov		edx, OFFSET msg1
	call	WriteString
	call	ReadDec
	mov		edi, [ebp + 8]
	mov		[edi], ax
	
	pop		ebp
	ret
GetNumVis endp

; Get Hourly Pay
GetHourPay proc
	push	ebp
	mov		ebp, esp

	mov		edx, OFFSET msg2
	call	WriteString
	call	ReadDec
	mov		edi, [ebp + 8]
	mov		[edi], ax

	pop		ebp
	ret
GetHourPay endp

;Get Hours Worked
GetHourWork proc
	mov		esi, OFFSET hours
	mov		edi, OFFSET mult
	movzx	ecx, numJobs
L1:	mov		edx, OFFSET msg3
	call	WriteString
	call	ReadDec
	mov		[esi], ax
	add		esi, TYPE hours

	mov		edx, OFFSET msg4
	call	WriteString
	call	ReadDec
	mov		[edi], ax
	add		edi, TYPE hours
	loop	L1	
	
	ret
GetHourWork endp

;Calculate Gross Pay
CalGrossPay proc
	mov		esi, OFFSET hours
	mov		edi, OFFSET mult
	movzx	ecx, numJobs
L2:	mov		ax, [esi]
	mov		bx, [edi]
	mul		bx
	add		totalHours, ax ;ax, totalHours
	add		esi, TYPE hours
	add		edi, TYPE mult	
	loop	L2
	mov		ax,	totalHours
	mov		bx, hourly
	mul		bx
	mov		grossPay, ax

	ret
CalGrossPay endp

; Display Pay
DispPay proc
	push	ebp
	mov		ebp, esp

	mov		edx, OFFSET msg5
	call	WriteString
	mov		ax, [ebp + 8]
	call	WriteDec
	mov		edx, OFFSET msg6
  	call	WriteString

	pop		ebp
	ret
DispPay endp

end main